package com.meiqi.edu.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Monica
 * @Date 2022/8/2 14:16
 **/
public interface OssService {
    //上传头像到OSS
    String uploadFileAvatar(MultipartFile file);

}
