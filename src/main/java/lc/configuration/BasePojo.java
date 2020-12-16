package lc.configuration;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;


/**
 * @description:
 * @author: lc
 * @time: 2020/9/17 14:10
 */
@Data
public abstract class BasePojo extends Model<BasePojo> {
    /**
     * 是否正序排序
     */
    @TableField(exist = false)
    public Boolean isAsc;

    /**
     * 排序字段
     */
    @TableField(exist = false)
    public String orderColumn;

    /**
     * @Author lc
     * @Description 返回字段类型的lambda方法，继承的子类使用时需重写
     * @Date 2020/9/17 15:29
     * @Param []
     * @return java.util.function.Function<?,java.lang.Object>
     */
    public SFunction<?, Object> getOrderColumnStr(String orderColumn) {
        return null;
    }

}
