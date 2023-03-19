package com.xw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PathUtils {
    /**
     * 生成的文件名为 yyyy/MM/dd/uuid.文件类型
     * @param fileName
     * @return
     */
    public static String generateFilePath(String fileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        int idx = fileName.lastIndexOf(".");
        String fileType = fileName.substring(idx);
        String saveName = new StringBuilder().append(datePath).append(uuid).append(fileType).toString();
        return saveName;
    }
}
