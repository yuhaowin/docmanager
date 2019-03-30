package com.chzu.controller;

import com.chzu.utils.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/files")
public class FilePreviewController {

    /**
     * 预览文档
     *
     * @param path
     * @param request
     * @return
     */
    @RequestMapping("/preview")
    public String convert(String path, HttpServletRequest request) {
        String basePath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/files/";
        String previewUrl = UploadUtil.convertFile2Html(basePath + path);
        // 跳转预览页面
        return "redirect:" + previewUrl;
    }
}
