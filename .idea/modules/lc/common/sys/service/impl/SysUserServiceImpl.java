package lc.common.sys.service.impl;

import lc.common.sys.entity.SysUser;
import lc.common.sys.dao.SysUserDao;
import lc.common.sys.service.SysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author lc
 * @since 2020-09-10 09:30:42
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

}