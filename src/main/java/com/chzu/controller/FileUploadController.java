package com.chzu.controller;

import com.chzu.utils.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/files")
public class FileUploadController {


	@RequestMapping("/upload")
    @ResponseBody
    public String appUpload(@RequestParam MultipartFile file, HttpServletRequest request){
        String basePath = request.getSession().getServletContext().getRealPath("/")+ "WEB-INF/files/";
        System.out.println("当前项目路径: " + basePath);

        String oldName = file.getOriginalFilename();

        System.out.println(oldName);

        String path = UploadUtil.uploadFile(file, basePath);

        System.out.println(path);

        return "{\"status\":200}";
    }
}
