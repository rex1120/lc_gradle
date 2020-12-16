package lc.common.sys.service;

import com.baomidou.mybatisplus.extension.api.R;
import lc.common.sys.entity.SysUser;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author lc
 * @since 2020-09-10 09:32:47
 */
public interface SysUserService extends IService<SysUser>{


    R loginAction(SysUser sysUser, HttpServletResponse response);

    SysUser saveOrUpdateUser(SysUser sysUser);
}