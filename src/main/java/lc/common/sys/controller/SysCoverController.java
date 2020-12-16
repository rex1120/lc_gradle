package lc.common.sys.controller;

import lc.common.sys.service.SysCoverService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 加密表(SysCover)表控制层
 *
 * @author lc
 * @since 2020-09-16 15:10:33
 */
@RestController
@RequestMapping("sysCover")
public class SysCoverController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysCoverService sysCoverService;


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}