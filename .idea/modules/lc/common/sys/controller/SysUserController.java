package lc.common.sys.controller;

import lc.common.sys.entity.SysUser;
import lc.common.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import io.bmdata.modules.sys.controller.AbstractController;
import javax.annotation.Resource;

/**
 * 用户表(SysUser)表控制层
 *
 * @author lc
 * @since 2020-09-10 09:30:43
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController extends AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;


}