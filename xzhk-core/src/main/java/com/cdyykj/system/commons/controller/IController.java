package com.cdyykj.system.commons.controller;


import java.util.Map;

/**
 * controller 接口约束
 */
public interface IController<T> {

    /**
     * 主页访问
     * @param queryParams
     * @return
     */
    String index(Map<String, Object> queryParams);

    /**
     * 主页列表json数据请求
     * @param queryParams
     * @return
     */
    Object listJson(Map<String, Object> queryParams);

    /**
     * 单数据页面
     * @param queryParams
     * @return
     */
    String single(Map<String, Object> queryParams);

    /**
     * 单条数据json查询
     * @param id
     * @return
     */
    Object getObjById(Integer id);

    /**
     * 数据插入
     * @param o
     * @return
     */
    Object insert(T o);

    /**
     * 数据更新
     * @param o
     * @return
     */
    Object update(T o);

    /**
     * 数据删除
     * @param id
     * @return
     */
    Object delete(Object id);
}
