package com.doux.oss.com.doux.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZhangTao
 * @create 2023-02-22-21:42
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
