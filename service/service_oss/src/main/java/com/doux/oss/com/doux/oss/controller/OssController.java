package com.doux.oss.com.doux.oss.controller;

import com.doux.commonutils.R;
import com.doux.oss.com.doux.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZhangTao
 * @create 2023-02-22-21:43
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "上传图片")
    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        //获取上传文件
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
