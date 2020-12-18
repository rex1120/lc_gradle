package lc.common.sys.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import lc.configuration.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表(SysUser)实体类
 *
 * @author lc
 * @since 2020-09-10 09:32:47
 */
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
@Data
public class SysUser extends BasePojo {

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
    @TableField(exist = false)
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
     * 出生日期 (注解@JsonFormat主要是后台到前台的时间格式的转换,注解@DataFormAT主要是前后到后台的时间格式的转换)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;
    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Long updateUserId;


    /**
     * 分页对象
     */
    @TableField(exist = false)
    public Page<SysUser> page;
    /**
     * token
     */
    @TableField(exist = false)
    public String token;

    /**
     * @return java.util.function.Function<lc.common.sys.entity.SysUser, java.lang.Object>
     * @Author lc
     * @Description 重写基础公共类方法
     * @Date 2020/9/17 15:29
     * @Param []
     */
    @Override
    public SFunction<SysUser, Object> getOrderColumnStr(String orderColumn) {
        SFunction<SysUser, Object> orderColumnFn ;
        if (orderColumn != null) {
            switch (orderColumn) {
                case "bornDate":
                    orderColumnFn = SysUser::getBornDate;
                    break;
                case "userName":
                    orderColumnFn = SysUser::getUserName;
                    break;
                case "loginName":
                    orderColumnFn = SysUser::getLoginName;
                    break;
                default:
                    this.isAsc = false;
                    orderColumnFn = SysUser::getCreateDate;
                    break;
            }
        } else {
            this.isAsc = false;
            orderColumnFn = SysUser::getCreateDate;
        }
        return orderColumnFn;
    }

}
