package lc.tool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lc.common.sys.entity.SysUser;
import lc.configuration.ConstantsPile;
import lc.tool.redis.RedisUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: lc
 * @time: 2020/12/18 10:03
 */
public class JwtUtil {
    /**
     * 生成签名,20分钟后过期
     */
    public static String sign(SysUser sysUser) {
        //获取存储在redis中的私钥
        String privateKey = RedisUtil.get(ConstantsPile.RSA_PRIVATE_KEY).toString();
        //过期时间 (DateUtil.offset时间偏移参考hutool的工具类)
        Date date = DateUtil.offset(DateUtil.date(), DateField.MINUTE, 20);
        Map<String, Object> tokenDataMap = new HashMap<>();
        tokenDataMap.put("id", sysUser.getId());
        tokenDataMap.put("loginName", sysUser.getLoginName());
        return Jwts.builder().setClaims(tokenDataMap).setExpiration(date).signWith(SignatureAlgorithm.HS512, privateKey).compact();
    }


    /**
     * 通过token获取用户信息
     * @Author lc
     * @Description //TODO
     * @Date 2020/12/18 16:35
     * @Param [response, token]
     * @return lc.common.sys.entity.SysUser
     */
    public static SysUser getUserByToken(HttpServletResponse response,String token) {
        return BeanUtil.mapToBean(getTokenMap(response,token), SysUser.class, true, new CopyOptions());
    }

    /**
     * 通过token和key值获取键值
     * @Author lc
     * @Description //TODO
     * @Date 2020/12/18 16:35
     * @Param [response, token, key]
     * @return java.lang.Object
     */
    public static Object getObjectByKey(HttpServletResponse response,String token,String key) {
        return Objects.requireNonNull(getTokenMap(response,token)).get(key);
    }

    private static Map<String, Object> getTokenMap (HttpServletResponse response,String token){
        //获取存储在redis中的私钥
        String privateKey = RedisUtil.get(ConstantsPile.RSA_PRIVATE_KEY).toString();
        try {
            return Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            response.setStatus(401);
            return null;
        }
    }
}
