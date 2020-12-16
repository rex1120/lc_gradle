package lc.common.sys.service.impl;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.api.R;
import lc.common.sys.entity.SysCover;
import lc.common.sys.service.SysCoverService;
import lc.configuration.ConstantsPile;
import lc.tool.RSAUtils;
import lc.common.sys.entity.SysUser;
import lc.common.sys.dao.SysUserDao;
import lc.common.sys.service.SysUserService;
import lc.tool.redis.RedisUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author lc
 * @since 2020-09-10 09:32:47
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysCoverService sysCoverService;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public R loginAction(SysUser sysUser, HttpServletResponse response) {
        SysUser queryUser = baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getLoginName, sysUser.getLoginName()));
        if (ObjectUtils.isEmpty(queryUser)) {
            return R.failed("账户不存在！");
        }
        try {
            //获取存储在redis中的私钥
            String privateKey = redisUtil.get(ConstantsPile.RSA_PRIVATE_KEY).toString();
            //rsa解密数据，获得原始密码
            String realPwd = RSAUtils.decrypt(sysUser.getPwd(), RSAUtils.getPrivateKey(privateKey));
            //找到数据库存入的des加密数据
            SysCover sysCover = sysCoverService.getOne(new QueryWrapper<SysCover>().lambda().eq(SysCover::getRelationId, queryUser.getId()).eq(SysCover::getUseFlag, 1));
            //初始化des
            DES des =  SecureUtil.des(HexUtil.decodeHex(sysCover.getDesKeyHex()));
            //des解密，获取加密内容
            String decodeStr = des.decryptStr(sysCover.getCoverStr());
            //匹配加密数据是否一致
            if (realPwd.equals(decodeStr)){
                return R.ok(queryUser).setMsg("登录成功！");
            }else{
                return R.failed("登录失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.failed("登录异常！");
    }

    @Override
    public SysUser saveOrUpdateUser(SysUser sysUser) {
        String rules = null;
        DES des = null;
        if (sysUser.getId() == null) {
            //随机生成密钥
            byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
            //构建des
            des = SecureUtil.des(key);
            //将字节转化为16进制字符串
            rules = HexUtil.encodeHexStr(key);
        }
        boolean result = sysUser.insertOrUpdate();
        if (result && rules != null) {
            SysCover sysCover = new SysCover();
            sysCover.setRelationId(sysUser.getId());
            sysCover.setDesKeyHex(rules);
            sysCover.setTableName("sys_user");
            sysCover.setCoverStr(des.encryptHex(sysUser.getPwd()));
            sysCoverService.save(sysCover);
        }
        return sysUser;
    }
}