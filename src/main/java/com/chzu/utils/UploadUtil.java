package com.chzu.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class UploadUtil {

    /**
     * 上传文件
     *
     * @param uploadFile
     * @param basePath
     * @return 返回文件存放的相对路径
     */
    public static String uploadFile(MultipartFile uploadFile, String basePath) {
        //取源文件名,以取得后缀名
        String oldName = uploadFile.getOriginalFilename();
        //生成新的文件名
        String newName = genFileName();
        newName += oldName.substring(oldName.lastIndexOf("."));
        //图片上传
        if (!basePath.endsWith("/")) {
            basePath += "/";
        }
        String relativePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String absolutePath = basePath + relativePath;
        uploadPicture(uploadFile, absolutePath, newName);
        return relativePath + "/" + newName;
    }


    /**
     * 文件上传
     *
     * @param uploadFile 上传文件
     * @param filePath   上传路径
     * @param fileName   文件名称
     * @return 成功返回 true  失败返回 false
     */
    public static Boolean uploadPicture(MultipartFile uploadFile, String filePath, String fileName) {
        if (!filePath.endsWith("/")) {
            filePath += "/";
        }
        createFile(filePath);
        try {
            uploadFile.transferTo(new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    private static void createFile(String path) {
        if (StringUtils.isNotEmpty(path)) {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }

    /**
     * 生成文件名
     *
     * @return
     */
    public static String genFileName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上4位随机数
        Random random = new Random();
        int end4 = random.nextInt(9999);
        //如果不足四位前面补0
        String imageName = millis + String.format("%04d", end4);
        return imageName;
    }

    /**
     * 测试函数
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(genFileName());
        }
    }

}
