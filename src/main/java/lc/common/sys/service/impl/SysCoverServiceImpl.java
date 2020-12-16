package lc.common.sys.service.impl;

import lc.common.sys.entity.SysCover;
import lc.common.sys.dao.SysCoverDao;
import lc.common.sys.service.SysCoverService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * 加密表(SysCover)表服务实现类
 *
 * @author lc
 * @since 2020-09-16 15:10:33
 */
@Service("sysCoverService")
public class SysCoverServiceImpl extends ServiceImpl<SysCoverDao, SysCover> implements SysCoverService {
    @Resource
    private SysCoverDao sysCoverDao;

}