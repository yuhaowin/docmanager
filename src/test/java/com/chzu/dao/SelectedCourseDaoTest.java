package com.chzu.dao;

import com.chzu.entity.ExcelDTO;
import com.chzu.entity.SelectedCourse;
import com.chzu.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml"})
public class SelectedCourseDaoTest {

    @Autowired
    private SelectedCourseDao selectedCourseDao;


    @Test
    public void countById() {
    }

    @Test
    public void delete() {
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(3);
        selectedCourse.setStudentId(10001);
        selectedCourseDao.delete(selectedCourse);
        System.out.println("2222");
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void updateByExample() {
        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseId(2);
        selectedCourse.setStudentId(123);
        selectedCourse.setMark(1);
        selectedCourseDao.updateByExample(selectedCourse);
    }

    @Test
    public void getByStudentId() {
        List<Integer> list = selectedCourseDao.getByStudentId(10006);
        System.out.println(list.size());
    }

    @Test
    public void getStudentsMark() throws Exception {
        List<ExcelDTO> studentsMark = selectedCourseDao.getStudentsMark(1);
        FileOutputStream out = new FileOutputStream("/Users/yuhao/Desktop/excel-test.xls");
        //1.创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置sheet名称
        String sheetName = "测试";
        ExcelUtils.exportExcelByPojo(workbook, sheetName, studentsMark);
        workbook.write(out);
        workbook.close();
        System.out.println(studentsMark.size());
    }
}