package lc.common.sys.dao;

import lc.common.sys.entity.SysCover;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * 加密表(SysCover)表数据库访问层
 *
 * @author lc
 * @since 2020-09-16 15:10:33
 */
@Mapper
public interface SysCoverDao  extends BaseMapper<SysCover>{

  

}