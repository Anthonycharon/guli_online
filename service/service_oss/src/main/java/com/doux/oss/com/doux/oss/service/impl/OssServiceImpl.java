package com.doux.oss.com.doux.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.doux.oss.com.doux.oss.service.OssService;
import com.doux.oss.com.doux.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author ZhangTao
 * @create 2023-02-22-21:42
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        //创建OSS实例
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            //上传文件流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String filename = file.getOriginalFilename();
            //防止多次上传文件重名覆盖问题，生成UUID值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            //按日期分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + "/" + uuid + filename;
            //上传文件的方法
            ossClient.putObject(bucketName, filename, inputStream);
            String url = "https://" + bucketName + "." + endPoint + "/" + filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭
            if (ossClient != null) {
                ossClient.shutdown();
            }

        }

    }
}
