package lc.tool;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;

/**
 * @description:
 * @author: lc
 * @time: 2020/9/3 10:58
 */
@Component
public class FileManage {
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


    public String  upload(MultipartFile multipartFile, String key) throws IOException {
        // 密钥配置
        Auth auth = Auth.create(accessKey,secretKey);
        //创建上传对象
        Configuration configuration = new Configuration();
        UploadManager uploadManager = new UploadManager(configuration);
        String result = "";
        FileInputStream inputStream=(FileInputStream)multipartFile.getInputStream();
        byte[] uploadBytes = new byte[inputStream.available()];
        ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
        Response res = null;
        try {
            //调用put方法上传
             res = uploadManager.put(byteInputStream,key,auth.uploadToken(bucketName),null, null);
            //打印返回的信息
            result = key;
        } catch (QiniuException e) {
            e.printStackTrace();
            // 请求失败时打印的异常的信息
            result = "no";
        }finally {
//            assert res != null;
//            res.close();
        }
        return  result;
    }

    //流获取输入的搜索关键字同行一行的语句
    public static ArrayList<String> isContainContent(String url, String keyWord) throws  Exception{
        File pathname = new File(url);
        ArrayList<String> jsonObjects = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        boolean result = false;
        //行读取
        LineNumberReader lineReader = null;
        InputStreamReader read = new InputStreamReader(new FileInputStream(pathname), "gbk");
        lineReader = new LineNumberReader(read);
        String readLine = null;
        while((readLine =lineReader.readLine()) != null){

            //判断是否包含
            if(readLine.contains(keyWord)) {
//                result = true;
//                    jsonObject.put("lineWords",readLine);
//                    jsonObject.put("lineNumber", lineReader.getLineNumber());
                jsonObject.put("pathname",pathname);
                jsonObjects.add(readLine);
            }
        }
        //关闭流
        if(lineReader != null){
            try {
                lineReader.close();
            } catch (IOException e) {
                e.printStackTrace();
//                lineReader = null;
            }
        }
//        jsonObject.put("flag", result);
        return jsonObjects;
    }
}
