package com.zjjhyzd.blog.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.google.common.collect.Lists;
import com.zjjhyzd.blog.controller.base.BaseController;
import com.zjjhyzd.blog.entity.OSSClientProperties;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {
    public static final String[] IMAGES_TYPES = new String[]{"png", "jpg", "gif", "webapp", "jpeg"};

    @Autowired
    private OSS ossClient;

    @Autowired
    private OSSClientProperties properties;

    @Value("${temp.path}")
    private String tempPath;

    public UploadController(HttpServletRequest request, HttpSession session) {
        super(request, session);
    }

    @PostMapping("/imgs")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile[] files) throws IOException {

        ArrayList<String> result = Lists.newArrayList();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);
            long size = file.getSize();
            if (size > 1024 * 1024 * 10) {
                return error("单个文件超过最大上传大小");
            }
            if (!Lists.newArrayList(IMAGES_TYPES).contains(extension)) {
                return error("只支持上传tar.gz|7z|zip|rar格式类型的文件");
            }

            LocalDate now = LocalDate.now();
            String fileName = IdUtil.simpleUUID() + "." + extension;
            String savePath = "static/" + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "/" + fileName;

            File tmpFile = new File(tempPath + File.separator + fileName);
            Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.25f).toFile(tmpFile);

            ossClient.putObject(properties.getBucketName(), properties.getBasePath() + "/" + savePath, tmpFile);

            result.add(properties.getDomain() + "/" + savePath);

            if (FileUtil.isFile(tmpFile)) {
                FileUtil.del(tmpFile);
            }
        }

        return success(result);
    }
}
