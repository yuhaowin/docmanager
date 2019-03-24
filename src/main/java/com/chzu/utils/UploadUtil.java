package com.chzu.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 文件上传下载工具类
 *
 * @author Eric_余浩
 * @version 1.0
 */
public class UploadUtil {

    public static Boolean uploadPicture(MultipartFile uploadFile, String imageBaseUrl) {
        //取源文件名,以取得后缀名
        String oldName = uploadFile.getOriginalFilename();
        //生成新的文件名
        String newName = genImageName();
        newName += oldName.substring(oldName.lastIndexOf("."));
        //图片上传
        if (!imageBaseUrl.endsWith("/")) {
            imageBaseUrl += "/";
        }
        String format = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String imagePath = imageBaseUrl + format;
        Boolean result = uploadPicture(uploadFile, imagePath, newName);
        return result;
    }


    /**
     * 文件上传
     *
     * @param uploadFile 上传文件
     * @param imagePath  上传路径
     * @param imageName  文件名称
     * @return 成功返回 true  失败返回 false
     */
    public static Boolean uploadPicture(MultipartFile uploadFile, String imagePath, String imageName) {
        if (!imagePath.endsWith("/")) {
            imagePath += "/";
        }
        createFile(imagePath);
        try {
            uploadFile.transferTo(new File(imagePath + imageName));
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
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上4位随机数
        Random random = new Random();
        int end4 = random.nextInt(9999);
        //如果不足四位前面补0
        String imageName = millis + String.format("%04d", end4);
        return imageName;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(genImageName());
        }
    }

}
