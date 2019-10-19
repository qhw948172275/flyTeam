package com.cdyykj.system.commons;

import org.joor.Reflect;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ListUtils {
    public static final String DEFAULT_FIELDNAME = "id";

    /**
     * 获取指定集合中，指定字段的ID集合
     *
     * @param fieldName 字段名称
     * @param list      集合
     * @return fieldName集合
     * @author kimi
     * @dateTime 2013-3-19 下午5:26:33
     */
    public static List<Integer> getIdsByList(String fieldName, List<? extends Object> list) {
        if (null != fieldName && !"".equals(fieldName)) {
            if (null != list && list.size() > 0) {
                List<Integer> results = new LinkedList<Integer>();
                for (int i = 0; i < list.size(); i++) {
                	Reflect r = Reflect.on(list.get(i));
                	if(r.field(fieldName)!=null){
                        results.add(Integer.parseInt(r.field(fieldName).get().toString()));
                    }
                }
                return results;
            }
        }
        return null;
    }

    /**
     * 获取指定集合中，指定字段的ID集合
     *
     * @param fieldName 字段名称
     * @param list      集合
     * @return fieldName集合
     * @author kimi
     * @dateTime 2013-3-19 下午5:26:33
     */
    public static List<String> getFieldsByList(String fieldName, List<? extends Object> list) {
        if (null != fieldName && !"".equals(fieldName)) {
            if (null != list && list.size() > 0) {
                List<String> results = new LinkedList<String>();
                for (int i = 0; i < list.size(); i++) {
                    Reflect r = Reflect.on(list.get(i));
                    if(r.field(fieldName).get()!=null){
                        results.add(r.field(fieldName).get().toString());
                    }
                }
                return results;
            }
        }
        return null;
    }

    /**
     * 获取指定集合中，指定字段的ID集合
     *
     * @param keyName 字段名称
     * @param list    集合
     * @return fieldName集合
     * @author kimi
     * @dateTime 2013-3-19 下午5:26:33
     */
    public static Map ListToMap(String keyName, List<? extends Object> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != keyName && !"".equals(keyName)) {
            if (null != list && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Reflect r = Reflect.on(list.get(i));
                    try {
                        Reflect field = r.field(keyName);
                        map.put(field.get().toString(), list.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return map;
    }

}
