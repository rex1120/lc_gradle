package lc.configuration;

import lc.tool.RSAUtils;
import lc.tool.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.Base64;

/**
 * @description:
 * @author: lc
 * @time: 2020/9/16 9:33
 */
//@Component
public class InitProjectBasics {

    @Resource
    private RedisUtil redisUtil;

    /**
     * @Author lc
     * @Description 初始化生成rsa的密钥，放入redis中
     * @Date 2020/9/16 9:37
     * @Param []
     * @return void
     */
    @PostConstruct
    public void  initRsaKey() throws Exception {
        //判断redis中没有rsa的私钥
        if (redisUtil.get(ConstantsPile.RSA_PRIVATE_KEY)==null){
            KeyPair keyPair = RSAUtils.getKeyPair();
            //生成公钥和私钥
            String privateKey = new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
            //将公钥和私钥存入redis中
            redisUtil.set(ConstantsPile.RSA_PRIVATE_KEY,privateKey);
            redisUtil.set(ConstantsPile.RSA_PUBLIC_KEY,publicKey);
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
        }
    }
}
