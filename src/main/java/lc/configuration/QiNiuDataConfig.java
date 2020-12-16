package lc.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: lc
 * @time: 2020/9/3 11:44
 */
@Data
@Component
public class QiNiuDataConfig {
    /**
     * 七牛域名domain
     */
    @Value("${oss.qiniu.domain}")
    private String domain;
    /**
     * 七牛ACCESS_KEY
     */
    @Value("${oss.qiniu.accessKey}")
    private String accessKey;
    /**
     * 七牛SECRET_KEY
     */
    @Value("${oss.qiniu.secretKey}")
    private String secretKey;
    /**
     * 七牛空间名
     */
    @Value("${oss.qiniu.bucketName}")
    private String bucketName;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
