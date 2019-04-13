package com.chzu.controller;

import com.chzu.entity.ExcelDTO;
import com.chzu.service.SelectedCourseService;
import com.chzu.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * 导出excel Controller
 */
@Controller
public class ExportExcelController {


    @Autowired
    private SelectedCourseService selectedCourseService;


    @RequestMapping("/teacher/export")
    public void exportExcel(HttpServletResponse response, Integer id) throws Exception {
        //设置response头信息
        response.reset();
        //改成输出excel文件
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=test.xls");
        OutputStream out = response.getOutputStream();
        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //准备导出excel数据；
        List<ExcelDTO> excelDTOList = selectedCourseService.getExcelDTOList(id);
        ExcelUtils.exportExcelByPojo(workbook, "测试", excelDTOList);
        workbook.write(out);

    }
}
