package lc.Controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.api.R;
import lc.tool.FileManage;
import lc.tool.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: lc
 * @time: 2020/9/3 12:58
 */
@RestController
@RequestMapping("FilesController")
public class FilesController {

    @Autowired
    FileManage fileManage;
    @Autowired
    QiniuUtils qiniuUtils;

    @CrossOrigin
    @RequestMapping("uploadFiles")
    public R uploadFiles(@RequestParam("file") MultipartFile file, @RequestParam Map<String, Object> params, HttpServletRequest request) throws IOException {
        return R.ok(qiniuUtils.uploadMultipartFile(file,params,true)) ;
//        return R.ok(fileManage.upload(files[0],files[0].getOriginalFilename()));
    }


    @Value("${upload.path}")
    private String uploadPath;


    /**
     * desc: 单文件上传
     * param:
     * return:
     * author: CDN
     * date: 2019/11/17
     */
    @CrossOrigin
    @PostMapping("/uploadOne")
    public Object uploadOne(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        String currentDate= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yyyyMMdd = uploadPath + currentDate + File.separator;
        if (!FileUtil.exist(yyyyMMdd)) {
            FileUtil.mkdir(yyyyMMdd);
        }
        String fileName = UUID.randomUUID().toString() + "@" + multipartFile.getOriginalFilename();
        String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        File file1 = FileUtil.writeBytes(multipartFile.getBytes(), yyyyMMdd + fileName);
        List<Map<String, String>> pathList = new ArrayList<>();
        if (file1.length() > 0) {
            Map<String, String> map = new HashMap<>();
            map.put("fileName", fileName);
            map.put("suffix", suffix);
            map.put("path", currentDate);
            pathList.add(map);
        }
        return pathList;
    }

    /**
     * desc: 多文件上传
     * param:
     * return:
     * author: CDN
     * date: 2019/11/17
     */
    @CrossOrigin
    @PostMapping("/uploadMany")
    public Object uploadMany(@RequestParam(value = "file") MultipartFile[] file) throws IOException {
        String currentDate=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yyyyMMdd = uploadPath + currentDate + File.separator;
        if (!FileUtil.exist(yyyyMMdd)) {
            FileUtil.mkdir(yyyyMMdd);
        }
        List<Map<String, String>> pathList = new ArrayList<>();
        if (file.length > 0) {
            for (MultipartFile multipartFile : file) {
                String fileName = UUID.randomUUID().toString() + "@" + multipartFile.getOriginalFilename();
                String suffix = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
                File file1 = FileUtil.writeBytes(multipartFile.getBytes(), yyyyMMdd + fileName);
                if (file1.length() > 0) {
                    Map<String, String> map = new HashMap<>();
                    map.put("fileName", fileName);
                    map.put("suffix", suffix);
                    map.put("path", currentDate);
                    pathList.add(map);
                }
            }
        }
        return pathList;
    }

    /**
     * desc: 图片显示
     * param:
     * return:
     * author: CDN
     * date: 2019/11/17
     */
    @CrossOrigin
    @PostMapping("showImg")
    public Object showImg(HttpServletResponse response, @RequestBody Map<String, Object> map) {
        if (map.isEmpty()) {
            return "文件不能为空";
        }
        boolean suffix = checkPic(map.get("suffix").toString());
        if (!suffix) {
            return "不是图片";
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtil.readBytes(map.get("path").toString() + map.get("fileName").toString()));
            IoUtil.close(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * desc: 文件下载
     * param:    http://127.0.0.1:83/download?path=20200709&fileName=63610ee-862c-4b55-9461-4c1ffe18e1c8@%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20200622141419.jpg
     * return:
     * author: CDN
     * date: 2019/11/17
     */
    @GetMapping("download")
    public Object download(HttpServletResponse response, @RequestParam Map<String, Object> map) throws IOException {
        if (map.isEmpty()) {
            return "文件不能为空";
        }
        String fileUrl =uploadPath+ map.get("path").toString() +File.separator+ map.get("fileName").toString();
//        String suffix = map.get("suffix").toString();
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/force-download");
        //        设置编码，避免文件名中文乱码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(map.get("fileName").toString().getBytes("gb2312"), "ISO8859-1") );
        outputStream.write(FileUtil.readBytes(fileUrl));
        IoUtil.close(outputStream);
        return null;
    }


    /**
     * desc: 图片格式检验
     * param:
     * return:
     * author: CDN
     * date: 2019/11/17
     */
    private static boolean checkPic(String suffix) {
        if (suffix.isEmpty()) {
            return false;
        }
        String reg = "(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.GIF|.gif|.BMP|.bmp)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("." + suffix);
        return matcher.find();
    }



}
