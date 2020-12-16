package lc.common.sys.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 文件存储表(SysFile)实体类
 *
 * @author lc
 * @since 2020-09-09 15:27:35
 */
@SuppressWarnings("serial")
@Data
public class SysFile extends Model<SysFile>  {
    
    private Long id;
    /**
    * 文件名称
    */
    private String fileName;
    /**
    * 文件路径
    */
    private String fileUrl;
    /**
    * 文件存放空间（云存储，主机存储）
    */
    private String fileLocation;
    /**
    * 文件后缀
    */
    private String fileSuffix;
    /**
    * 文件大小(单位：B)
    */
    private Long fileSize;
    /**
    * 关联id
    */
    private Long relationId;
    /**
    * 关联表名
    */
    private String relationTable;
    /**
    * 上传人id
    */
    private String uploadUser;
    /**
    * 删除标记（1：未删，0：已删）
    */
    private Integer deleteFlag;
    /**
    * 标签
    */
    private String tag;
    /**
    * 上传日期
    */
    private Date uploadDate;
    /**
     * 用途分类
     */
    private String  assortment;
    /**
     * hash码
     */
    private String  hashCode;
}