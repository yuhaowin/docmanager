package com.chzu.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class UploadUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String CONVERT_URL = "http://dcs.yozosoft.com:80/upload";

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
        try {
            newName += oldName.substring(oldName.lastIndexOf("."));
        } catch (Exception e) {
            newName += oldName;
        }

        //图片上传
        if (!basePath.endsWith("/")) {
            basePath += "/";
        }
        String relativePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String absolutePath = basePath + relativePath;
        uploadPicture(uploadFile, absolutePath, newName);
        return "/" + relativePath + "/" + newName;
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
     * 将本地文件转化为可预览的html链接
     *
     * @param filepath 文件路径
     * @return 预览链接
     */
    public static String convertFile2Html(String filepath) {
        String previewUrl = "";
        String requestJson = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(CONVERT_URL);
            FileBody file = new FileBody(new File(filepath));
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            HttpEntity httpEntity = entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(Charset.forName("UTF-8"))
                    .addPart("file", file)
                    //自定义 convertType为 1 文档格式到html的转换
                    .addPart("convertType", new StringBody("1", ContentType.TEXT_PLAIN))
                    .build();
            httppost.setEntity(httpEntity);
            HttpResponse response = httpclient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                requestJson = EntityUtils.toString(resEntity);
                EntityUtils.consume(resEntity);
                JsonNode jsonNode = MAPPER.readTree(requestJson);
                previewUrl = jsonNode.get("data").get(0).asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (Exception ignore) {
            }
        }
        return previewUrl;
    }

    /**
     * 测试函数
     *
     * @param args
     */
    public static void main(String[] args) {
        String convertByFile = convertFile2Html("/Users/yuhao/Desktop/123.docx");
        System.out.println(convertByFile);
    }
}
