package com.xw.service;

import com.xw.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult upLoadImg(MultipartFile img);
}
