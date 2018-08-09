package com.nzgreens.common.common.utils;

import com.nzgreens.common.common.enums.FileTypeEnum;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.UUID;

/**
 * 文件上传工具类
 * Created by sylar on 2017/7/13.
 */
public class FileUploadUtil {
    public static final String basePath = File.separator + "data" + File.separator + "upload" + File.separator;

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
        System.out.println(getFileExtendName(fileBytes));
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
        return uploadFileName;
    }

    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = ".jpg";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70)
                && (photoByte[3] == 56) && ((photoByte[4] == 55) || (photoByte[4] == 57))
                && (photoByte[5] == 97)) {
            strFileExtendName = ".gif";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73)
                && (photoByte[9] == 70)) {
            strFileExtendName = ".jpg";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            strFileExtendName = ".bmp";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            strFileExtendName = ".png";
        }
        return strFileExtendName;
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
            if (index < 0) {
                fileName = ".png";
            } else {
                fileName = fileName.substring(index, fileName.length());
            }
            return UUID.randomUUID().toString().replace("-", "") + fileName;
        }
        return "";
    }

    public static void main(String[] args) {
        File file =new File("C:\\Users\\sylar\\Pictures\\5934d242d0fe8.png");
        FileInputStream fis = null;
        System.out.println("C:\\Users\\sylar\\Pictures\\5934d242d0fe8png".lastIndexOf("."));
     /*   try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len=0;
        try {
            while((len=fis.read(buf))!=-1){
                baos.write(buf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = baos.toByteArray();


        uploadFile(1L,FileTypeEnum._FREIGHT,"!.s",data);*/
    }
}
