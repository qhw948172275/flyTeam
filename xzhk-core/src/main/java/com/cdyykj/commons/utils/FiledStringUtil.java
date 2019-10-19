package com.cdyykj.commons.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 将类属性转换成List<String>
 */
public class FiledStringUtil {
    /**
     *
     * @param classList List<Object>
     * @param remove  表示后几位属性不转换
     * @return
     * @throws IllegalAccessException
     */
    public static List<List<String>> getListStringByClass(Object classList, int remove) throws IllegalAccessException {
        List<Object> objects=(List<Object>)classList;

        List<List<String>> lists=new ArrayList<>();
        for (Object object:objects){
            Field[] fields=object.getClass().getDeclaredFields();
            List<String> stringList=new ArrayList<>();
            for(int i=0;i<fields.length-remove;i++){
                Boolean b=fields[i].isAccessible();
                 fields[i].setAccessible(true);
                 if(fields[i].get(object)!=null){
                     stringList.add(fields[i].get(object).toString());
                 }else{
                     stringList.add("");
                 }
                fields[i].setAccessible(b);
            }
            lists.add(stringList);
        }
        return lists;
    }

}
