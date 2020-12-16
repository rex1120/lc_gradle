package lc.tool;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @description:
 * @author: lc
 * @time: 2020/9/11 9:28
 */
public class StringConversionUtils {
    /**
     * @Author lc
     * @Description 经过md5加密后生成新的字符串
     * @Date 2020/9/11 9:33
     * @Param [value]
     * @return java.lang.String
     */
    public static String toMd5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(value.getBytes(StandardCharsets.UTF_8));
            //利用hutool工具类将字节转换为16进制码
            return HexUtil.encodeHexStr(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {

//        899bc720ecec79f4
//        DES des =  SecureUtil.des(HexUtil.decodeHex("899bc720ecec79f4"));
//
//        System.out.println(des.decryptStr("6bde62f8b106eccc462d2f88b50320ce"));

//        String content = "test中文";
//
//        //随机生成密钥
//        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
//
//        //构建
//        DES des = SecureUtil.des(key);
//        String rules = HexUtil.encodeHexStr(key);
//        System.out.println(rules);
//        //加密解密
//        byte[] encrypt = des.encrypt(content);
//        byte[] decrypt = des.decrypt(encrypt);
//
//        //加密为16进制，解密为原字符串
//        String encryptHex = des.encryptHex(content);
//        System.out.println(encryptHex);
//        String decryptStr = des.decryptStr(encryptHex);
//        System.out.println(decryptStr);


//        //用RSA的工具类创建公钥和私钥
//        RASCover rasCover = new RASCover();
//        rasCover.generateKey();
//        String rsaStr = rasCover.encrypt("90AAC572651132CE4C961C9E5DEB93FE",rasCover.getPublicKey());
//        System.out.println(rsaStr);
//        //公钥
//        System.out.println("公钥======》");
//        System.out.println(rasCover.getPublicKey());
//        System.out.println("《============公钥");
//        //解密后
//        System.out.println(rasCover.decrypt(rsaStr,rasCover.getPrivateKey()));
    }

}
