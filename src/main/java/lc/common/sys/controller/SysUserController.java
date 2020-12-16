package lc.common.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.api.R;
import lc.common.sys.entity.SysUser;
import lc.common.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Function;

/**
 * 用户表(SysUser)表控制层
 *
 * @author lc
 * @since 2020-09-10 09:32:47
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;


    /**
     * @Author lc
     * @Description 保存或更新用户信息
     * @Date 2020/9/10 9:35
     * @Param [sysUser]
     * @return com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("saveOrUpdateUser")
    public R saveOrUpdateUser(@RequestBody SysUser sysUser) throws Exception {
        return R.ok(sysUserService.saveOrUpdateUser(sysUser));
    }


    /**
     * @Author lc
     * @Description 删除用户
     * @Date 2020/9/10 9:36
     * @Param [sysUser]
     * @return com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("deleteUser")
    public R deleteUser(@RequestBody SysUser sysUser){
        return R.ok(sysUserService.removeById(sysUser));
    }

    /**
     * @Author lc
     * @Description 用户分页列表
     * @Date 2020/9/10 9:45
     * @Param [sysUser]
     * @return com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("userPageList")
    public R userPageList(@RequestBody SysUser sysUser){
        return R.ok( sysUserService.page(sysUser.getPage(),new QueryWrapper<SysUser>().lambda().eq(sysUser.getGender()!=null,SysUser::getGender,sysUser.getGender()).like(StringUtils.isNotEmpty(sysUser.getUserName()),SysUser::getUserName,sysUser.getUserName()) .orderBy(true,sysUser.getIsAsc(), sysUser.getOrderColumnStr(sysUser.getOrderColumn()))));
    }


    @PostMapping("loginAction")
    public R loginAction(@RequestBody SysUser sysUser,HttpServletResponse response){
        return sysUserService.loginAction(sysUser,response);
    }


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }


}