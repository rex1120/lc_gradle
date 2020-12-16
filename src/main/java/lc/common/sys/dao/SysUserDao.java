package lc.common.sys.dao;

import lc.common.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author lc
 * @since 2020-09-10 09:32:47
 */
@Mapper
public interface SysUserDao  extends BaseMapper<SysUser>{

  

}