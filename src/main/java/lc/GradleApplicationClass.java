package lc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: lc
 * @time: 2020/8/27 10:53
 */
@SpringBootApplication
@MapperScan("lc.common.*.mapper")
public class GradleApplicationClass {
    public static void main(String[] args){
        SpringApplication.run(GradleApplicationClass.class,args);
    }
}
