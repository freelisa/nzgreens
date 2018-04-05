package com.nzgreens.common.common.utils;

import com.nzgreens.common.common.enums.FileTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具类
 * Created by sylar on 2017/7/13.
 */
public class FileUploadUtil {
    public static final String basePath = File.separator + "data" + File.separator + "files" + File.separator;

    /**
     * 上传文件
     * @param id           （用户id或图文id,对应枚举类,无id传null即可）
     * @param fileTypeEnum 文件枚举类
     * @param fileName     文件名(a.png)
     * @param fileBytes    文件字节数组
     * @return
     */
    public static String uploadFile(Long id, FileTypeEnum fileTypeEnum, String fileName, byte[] fileBytes) {
        String filePath = getUploadFilePath(id, fileTypeEnum);
        /**
         * 上传文件目录
         */
        File uploadPath = new File(filePath);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String uploadFileName = getUploadFileName(fileName);
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(filePath + File.separator + uploadFileName));
            try {
                out.write(fileBytes);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePath.replace(basePath, "") + File.separator + uploadFileName;
    }


    /**
     * 获取上传路径
     *
     * @param id
     * @param fileTypeEnum
     * @return
     */
    private static String getUploadFilePath(Long id, FileTypeEnum fileTypeEnum) {
        if (id == null)
            return basePath + fileTypeEnum.getPath();
        return basePath + fileTypeEnum.getPath() + File.separator + id;
    }

    /**
     * 获取上传文件名称
     *
     * @param fileName
     * @return
     */
    private static String getUploadFileName(String fileName) {
        if (StringUtils.isNotBlank(fileName)) {
            int index = fileName.lastIndexOf(".");
            fileName = fileName.substring(index, fileName.length());
            return UUID.randomUUID().toString().replace("-", "") + fileName;
        }
        return "";
    }
}
