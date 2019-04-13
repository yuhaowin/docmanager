package com.chzu.excelannotation;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ExcelMappingAction {

    public static TupleTwo<String[], String[]> handle(Class<?> clazz){
        Field[] declaredFields = clazz.getDeclaredFields();

        List<String> titles = new ArrayList<>();
        List<String> attributes = new ArrayList<>();

        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            ExcelMapping excelMapping = field.getAnnotation(ExcelMapping.class);
            if (excelMapping == null){
                continue;
            }
            titles.add(excelMapping.value());
            attributes.add(field.getName());
        }
        return new TupleTwo<>(titles.toArray(new String[titles.size()]), attributes.toArray(new String[attributes.size()]));
    }
}
