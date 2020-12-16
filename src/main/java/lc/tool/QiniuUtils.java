package lc.tool;

/**
 * @description:
 * @author: lc
 * @time: 2020/9/3 16:41
 */

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lc.common.sys.entity.SysFile;
import lc.common.sys.service.SysFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 七牛云工具类
 */
@Component
public class QiniuUtils {
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
    private String bucket;

    /**
     * 文件类Service
     */
    @Resource
    private SysFileService sysFileService;


    /**
     * @Author lc
     * @Description 删除文件
     * @Date 2020/9/9 17:37
     * @Param [fileName]
     * @return void
     */
    public void deleteFileFromQiniu(String fileName) {
        Configuration cfg = new Configuration();
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }


    /**
     * @Author lc
     * @Description 上传到七牛云空间
     * @Date 2020/9/9 16:36
     * @Param [multipartFile, params, override]
     * @return lc.common.sys.entity.SysFile
     */
    public SysFile uploadMultipartFile(MultipartFile multipartFile, Map<String, Object> params, boolean override) {
        SysFile sysFile = null;
        Configuration cfg = new Configuration();
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken;
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String saveName = IdUtil.simpleUUID()+suffix;
            if (override) {
                //覆盖上传凭证
                upToken = auth.uploadToken(bucket, saveName);
            } else {
                upToken = auth.uploadToken(bucket);
            }
            //默认上传接口回复对象
            DefaultPutRet putRet;
            //进行上传操作，传入文件的字节数组，文件名，上传空间，得到回复对象
            Response response = uploadManager.put(multipartFile.getBytes(), saveName, upToken);
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            if (putRet != null) {
                sysFile = new SysFile();
                sysFile.setFileSize(multipartFile.getSize());
                sysFile.setFileName(multipartFile.getOriginalFilename());
                sysFile.setFileUrl(domain+"/"+saveName);
                sysFile.setHashCode(putRet.hash);
                sysFile.setFileSuffix(putRet.key.substring(putRet.key.lastIndexOf(".")));
                sysFile.setAssortment(params.get("assortment") != null ? params.get("assortment").toString() : "none");
                sysFileService.save(sysFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysFile;
    }
}

