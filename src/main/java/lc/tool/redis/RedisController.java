package lc.tool.redis;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @author: lc
 * @time: 2020/9/15 17:22
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    /**
     * redis中存储的过期时间60s
     */
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    /**
     * @Author lc
     * @Description 往redis缓存中塞入数据
     * @Date 2020/9/16 9:21
     * @Param [params]
     * @return com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("redisSet")
    public R redisSet(@RequestBody Map<String,Object>  params){
//        return redisUtil.set(key,value,ExpireTime);

        return R.ok(redisUtil.set(params.get("key").toString(),params.get("value")));
    }

    /**
     * @Author lc
     * @Description 从redis缓存中取出数据
     * @Date 2020/9/16 9:20
     * @Param [params]
     * @return com.baomidou.mybatisplus.extension.api.R
     */
    @PostMapping("redisGet")
    public R redisGet(@RequestBody Map<String,Object>  params){
        return R.ok(redisUtil.get(params.get("key").toString()));
    }

    @PostMapping("expire")
    public R expire(String key){
        return R.ok(redisUtil.expire(key,ExpireTime));
    }
}

