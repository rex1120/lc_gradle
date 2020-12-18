package lc.configuration;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lc.common.sys.entity.SysUser;
import lc.tool.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: lc
 * @time: 2020/12/18 14:10
 */
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle被调用");
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            response.setStatus(401);
            return false;
        }
        SysUser sysUser = JwtUtil.getUserByToken(response,token);
        System.out.println("token校验结果："+ ObjectUtil.isNotEmpty(sysUser));
        return ObjectUtil.isNotEmpty(sysUser);
    }

}
