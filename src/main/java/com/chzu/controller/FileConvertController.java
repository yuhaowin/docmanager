package com.chzu.controller;

import com.chzu.utils.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/files")
public class FileConvertController {

    /**
     * 预览文件
     *
     * @param path
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public String convert(String path, HttpServletRequest request) {
        String basePath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/files/";
        String url = UploadUtil.convertFile2Html(basePath + path);
        return "redirect:" + url;
    }
}
