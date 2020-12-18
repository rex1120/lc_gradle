package lc.tool;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自动填充器
 * @description: 为相关空值属性提供默认值
 * @author: lc
 * @time: 2020/11/24 16:10
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    /**
     * 默认值，新增情景
     * @Author lc
     * @Description //TODO
     * @Date 2020/12/18 16:40
     * @Param [metaObject]
     * @return void
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)){
            Long id = (Long) JwtUtil.getObjectByKey(response,token,"id");
            this.setFieldValByName("createUserId", id,metaObject);
        }
        this.setFieldValByName("createDate", new Date(),metaObject);
        this.setFieldValByName("updateDate", new Date(),metaObject);

    }

    /**
     * 默认值，更新情景
     * @Author lc
     * @Description //TODO
     * @Date 2020/12/18 16:41
     * @Param [metaObject]
     * @return void
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateDate", new Date(),metaObject);
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)){
            Long id = (Long) JwtUtil.getObjectByKey(response,token,"id");
            this.setFieldValByName("updateUserId", id,metaObject);
        }
    }
}
