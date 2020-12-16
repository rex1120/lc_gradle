package lc.common.sys.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 用户表(SysUser)实体类
 *
 * @author lc
 * @since 2020-09-10 09:30:39
 */
@SuppressWarnings("serial")
@Data
public class SysUser extends Model<SysUser>  {
    
    private Long id;
    /**
    * 姓名（昵称）
    */
    private String userName;
    /**
    * 用户名
    */
    private String loginName;
    /**
    * 密码
    */
    private String pwd;
    /**
    * 头像地址
    */
    private String headPicture;
    /**
    * 地址
    */
    private String address;
    /**
    * 出生日期
    */
    private Date bornDate;
    /**
    * 联系方式
    */
    private Object contactWay;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * 更新时间
    */
    private Date updateDate;
    /**
    * 性别
    */
    private Integer gender;
}