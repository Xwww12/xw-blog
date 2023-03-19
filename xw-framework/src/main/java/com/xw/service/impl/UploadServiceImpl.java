package com.xw.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.xw.domain.ResponseResult;
import com.xw.enums.AppHttpCodeEnum;
import com.xw.exception.SystemException;
import com.xw.service.UploadService;
import com.xw.utils.PathUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class UploadServiceImpl implements UploadService {
    //...生成上传凭证，然后准备上传
    @Value("${oss.accessKey}")
    private String accessKey;
    @Value("${oss.secretKey}")
    private String secretKey;
    @Value("${oss.bucket}")
    private String bucket;

    @Override
    public ResponseResult upLoadImg(MultipartFile img) {
        // 判断文件类型
        if (img == null) {
            throw new SystemException(AppHttpCodeEnum.FILE_EMPTY);
        }
        String originalFilename = img.getOriginalFilename();
        if (originalFilename == null || !originalFilename.endsWith(".png")) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        // 生成文件保存名
        String saveName = PathUtils.generateFilePath(originalFilename); // 如2023/3/18/a.png
        // 上传文件到OSS
        String url = uploadOss(img, saveName);

        return ResponseResult.okResult(url);
    }

    /**
     * 上传文件到七牛云OSS
     * @param img
     * @param saveName
     * @return
     */
    private String uploadOss(MultipartFile img, String saveName) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = saveName;
        try {
            InputStream inputStream = img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                // System.out.println(putRet.key);
                // System.out.println(putRet.hash);

                return "http://rro56zvtn.hn-bkt.clouddn.com/" + key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }
}
