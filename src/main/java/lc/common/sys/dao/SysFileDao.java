package lc.common.sys.dao;

import lc.common.sys.entity.SysFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件存储表(SysFile)表数据库访问层
 *
 * @author lc
 * @since 2020-09-09 15:27:36
 */
@Mapper
public interface SysFileDao  extends BaseMapper<SysFile>{

  

}