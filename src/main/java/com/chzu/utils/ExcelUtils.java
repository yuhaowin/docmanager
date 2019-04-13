package com.chzu.utils;

import com.chzu.excelannotation.ExcelMapping;
import com.chzu.excelannotation.ExcelMappingAction;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 导出Excel工具类
 */
public class ExcelUtils {

    /**
     * 将pojo形式的数据源导出Excel
     *
     * @param workbook  工作薄(一个Excel文档)
     * @param sheetName 工作表(sheet)名称
     * @param dataList  数据源list
     * @param <T>
     * @throws Exception
     */
    public static <T> void exportExcelByPojo(HSSFWorkbook workbook, String sheetName, List<T> dataList) throws Exception {

        //中文字段名(对应Excel的标题)
        String[] cnFields = ExcelMappingAction.handle(dataList.get(0).getClass()).getExcelTitle();
        //英文字段名(对应pojo的属性)
        String[] enFields = ExcelMappingAction.handle(dataList.get(0).getClass()).getPojoAttribute();

        //创建工作表sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //创建标题行;并且设置标题
        HSSFRow titleRow = sheet.createRow(0);
        for (int i = 0; i < cnFields.length; i++) {
            HSSFCell titleCell = titleRow.createCell(i);
            titleCell.setCellValue(cnFields[i]);
        }
        //操作单元格;将数据写入单元格
        if (dataList != null) {
            for (int i = 0; i < dataList.size(); i++) {
                //创建数据行,第一行为行标题
                HSSFRow DataRow = sheet.createRow(i + 1);
                for (int j = 0; j < enFields.length; j++) {
                    HSSFCell itemCell = DataRow.createCell(j);
                    Object objValue = getFieldValueByNameSequence(enFields[j], dataList.get(i));
                    String fieldValue = objValue == null ? "" : objValue.toString();
                    itemCell.setCellValue(fieldValue);
                }

            }
        }
    }


    /**
     * @param fieldNameSequence 带路径的属性名或简单属性名
     * @param o                 对象
     * @return 属性值
     * @throws Exception
     * @MethodName : getFieldValueByNameSequence
     * @Description :
     * 根据带路径或不带路径的属性名获取属性值
     * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     */
    private static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {

        Object value = null;
        //将fieldNameSequence进行拆分
        String[] attributes = fieldNameSequence.split("\\.");
        if (attributes.length == 1) {
            value = getFieldValueByName(fieldNameSequence, o);
        } else {
            //根据属性名获取属性对象
            Object fieldObj = getFieldValueByName(attributes[0], o);
            String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
            value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value;
    }

    /**
     * @param fieldName 字段名
     * @param o         对象
     * @return 字段值
     * @MethodName : getFieldValueByName
     * @Description : 根据字段名获取字段值
     */
    private static Object getFieldValueByName(String fieldName, Object o) throws Exception {
        Object value = null;
        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            value = field.get(o);
        }
        return value;
    }

    /**
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     * @MethodName : getFieldByName
     * @Description : 根据属性名(String)获取获取到类的属性(Field)
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        //拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();
        //如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        //则返回空
        return null;
    }

    /**
     * @param workbook
     * @param fontsize
     * @return 单元格样式
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize, boolean flag, boolean flag1) {
        HSSFCellStyle style = workbook.createCellStyle();
        //是否水平居中
        if (flag1) {
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        }
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //创建字体
        HSSFFont font = workbook.createFont();
        //是否加粗字体
        if (flag) {
            font.setBold(true);
        }
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        return style;
    }

    public static void main(String[] args) throws Exception {

        FileOutputStream out = new FileOutputStream("/Users/yuhao/Desktop/excel-test.xls");

        //设置response头信息
//        response.reset();
//        response.setContentType("application/vnd.ms-excel");        //改成输出excel文件
//        response.setHeader("Content-disposition","attachment; filename="+fileName+".xls" );
//        OutputStream out=response.getOutputStream();

        //1.创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //准备数据源(pojo)
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            City city = new City("城市名称： " + i, "城市地址：" + i);
            list.add(city);
        }

        //设置sheet名称
        String sheetName = "测试";
        ExcelUtils.exportExcelByPojo(workbook, sheetName, list);
        workbook.write(out);
        workbook.close();

    }
}


/**
 * 测试使用的内部类
 */
class City {
    @ExcelMapping("城市名称")
    private String name;
    @ExcelMapping("城市地址")
    private String addr;
    private String zip;

    public City(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
}

