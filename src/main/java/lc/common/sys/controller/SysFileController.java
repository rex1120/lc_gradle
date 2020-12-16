package lc.common.sys.controller;

import lc.common.sys.service.SysFileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件存储表(SysFile)表控制层
 *
 * @author lc
 * @since 2020-09-09 15:27:38
 */
@RestController
@RequestMapping("sysFile")
public class SysFileController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysFileService sysFileService;


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}