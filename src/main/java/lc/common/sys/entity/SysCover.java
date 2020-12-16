package lc.common.sys.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 加密表(SysCover)实体类
 *
 * @author lc
 * @since 2020-09-16 15:10:33
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data
public class SysCover extends Model<SysCover>  {

    private Long id;
    /**
    * 关联id
    */
    private Long relationId;
    /**
    * 关联表名
    */
    private String tableName;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * 使用状态（1：使用中，0：未使用）
    */
    private Integer useFlag;
    /**
    * 16位des的key
    */
    private String desKeyHex;
    /**
    * 加密的内容
    */
    private String coverStr;
}
