package lc.common.sys.service.impl;

import lc.common.sys.entity.SysFile;
import lc.common.sys.dao.SysFileDao;
import lc.common.sys.service.SysFileService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * 文件存储表(SysFile)表服务实现类
 *
 * @author lc
 * @since 2020-09-09 15:27:38
 */
@Service("sysFileService")
public class SysFileServiceImpl extends ServiceImpl<SysFileDao, SysFile> implements SysFileService {
    @Resource
    private SysFileDao sysFileDao;

}