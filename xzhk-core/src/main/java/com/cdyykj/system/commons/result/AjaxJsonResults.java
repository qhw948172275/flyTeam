package com.cdyykj.system.commons.result;

import com.cdyykj.system.commons.controller.CallbackAjax;
import com.cdyykj.system.commons.controller.CallbackPageInfo;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

public class AjaxJsonResults {

    /**
     * 返回页面ajax
     *
     * @return Map
     */
    public static Map<String, Object> returnBootTable(CallbackPageInfo ajax, boolean isOutError) {
        PageInfo o = new PageInfo();
        try {
            o = ajax.callback();
            return returnBootTable(true, "执行成功!", o);

        } catch (RuntimeException e) {
            e.printStackTrace();
            if (isOutError) {
                return returnBootTable(false, "执行失败!" + e.getMessage(), o);
            }
            return returnBootTable(false, "执行失败!", o);
        }
    }

    /**
     * 返回页面ajax
     *
     * @return Map
     */
    public static Map<String, Object> returnBootTable(CallbackPageInfo ajax, boolean isOutError, String successMsg, String errorMsg) {
        PageInfo o = new PageInfo();
        try {
            o = ajax.callback();
            return returnBootTable(true, successMsg, o);

        } catch (RuntimeException e) {
            e.printStackTrace();
            if (isOutError) {
                return returnBootTable(false, errorMsg + e.getMessage(), o);
            }
            return returnBootTable(false, errorMsg, o);
        }
    }

    /**
     * 返回bootstrap内容
     *
     * @param status 成功 true，失败 false
     * @param info   提示消息
     * @param page   {@code PageInfo}
     * @return <p>{
     * <p>     status : 成功 true，失败 false
     * <p>     msg :    提示消息
     * <p>     total : 总计数量
     * <p>     rows :  当前分页数据
     * <p>}
     */
    public static Map<String, Object> returnBootTable(boolean status, String info, PageInfo page) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("msg", info);
        map.put("total", page.getTotal());
        map.put("rows", page.getList());
        return map;
    }

    /**
     * 返回页面table，bootstrap table 最开始的地方可能用到过
     *
     * @param status   {成功：true, 失败：false}
     * @param msg      提示信息
     * @param pageInfo {@code PageInfo}分页实体
     * @return
     */
    public static Map<String, Object> returnTable(boolean status, String msg, Object pageInfo) {
        return returnAjax(status, msg, pageInfo, null);
    }

    /**
     * 返回页面ajax
     *
     * @return Map
     */
    public static Map<String, Object> returnAjax(CallbackAjax ajax, boolean isOutError) {
        try {
            Object o = ajax.callback();
            return returnAjax(true, "执行成功!", o, null);

        } catch (RuntimeException e) {
            e.printStackTrace();
            if (isOutError) {
                return returnAjax(false, "执行失败!" + e.getMessage(), null, null);
            }
            return returnAjax(false, "执行失败!", null, null);
        }
    }

    /**
     * 返回页面ajax
     *
     * @return Map
     */
    public static Map<String, Object> returnAjax(CallbackAjax ajax, boolean isOutError, String successMsg, String errorMsg) {
        Object o = new Object();
        try {
            o = ajax.callback();
            return returnAjax(true, successMsg, o, null);

        } catch (RuntimeException e) {
            e.printStackTrace();
            if (isOutError) {
                return returnAjax(false, errorMsg + e.getMessage(), o, null);
            } else {
                return returnAjax(false, errorMsg, o, null);
            }
        }
    }

    /**
     * 返回页面ajax
     *
     * @param status {成功：true, 失败：false}
     * @param msg    提示信息
     * @return Map
     */
    public static Map<String, Object> returnAjax(boolean status, String msg) {
        return returnAjax(status, msg, null, null);
    }

    /**
     * 返回页面ajax
     *
     * @param status {成功：true, 失败：false}
     * @param msg    提示信息
     * @param data   返回的数据
     * @return Map
     */
    public static Map<String, Object> returnAjax(boolean status, String msg, Object data) {
        return returnAjax(status, msg, data, null);
    }


    /**
     * 返回页面ajax
     *
     * @param status {成功：true, 失败：false}
     * @param msg    提示信息
     * @param code   提示代码
     * @param data   返回的数据
     * @return Map
     */
    public static Map<String, Object> returnAjax(boolean status, String msg, Object data, String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("msg", msg);
        map.put("data", data);
        map.put("code", code);
        return map;
    }

//    public static void main(String[] args) {
//        System.out.println(returnTable(false,"你大爷",new PageInfo<BIConversion.User>()));
//        System.out.println(returnAjax(false,"你大爷"));
//        System.out.println(returnAjax(false,"你大爷",new PageInfo<BIConversion.User>()));
//        System.out.println(returnAjax(false,"你大爷",new PageInfo<BIConversion.User>(),"999"));
//
//    }

}
