package lc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: lc
 * @time: 2020/12/18 14:18
 */
@Configuration
public class MywebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/*/**")
                .excludePathPatterns("/sysUser/loginAction", "/redis/**")
        ;
    }

}

