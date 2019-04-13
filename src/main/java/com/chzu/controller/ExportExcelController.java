package com.chzu.controller;

import com.chzu.entity.CourseCustom;
import com.chzu.entity.ExcelDTO;
import com.chzu.service.CourseService;
import com.chzu.service.SelectedCourseService;
import com.chzu.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 导出excel Controller
 */
@Controller
public class ExportExcelController {


    @Autowired
    private SelectedCourseService selectedCourseService;

    @Autowired
    private CourseService courseService;


    /**
     * 导出学生分数excel
     *
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping("/teacher/export")
    public void exportExcel(HttpServletResponse response, Integer id) throws Exception {
        CourseCustom course = courseService.findById(id);
        String fileName = "【" + course.getCourseName() + "】课程学生分数表.xls";
        //设置response头信息
        response.reset();
        //改成输出excel文件
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream out = response.getOutputStream();
        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //准备导出excel数据；
        List<ExcelDTO> excelDTOList = selectedCourseService.getExcelDTOList(id);
        ExcelUtils.exportExcelByPojo(workbook, "学生分数表", excelDTOList);
        workbook.write(out);
    }
}
