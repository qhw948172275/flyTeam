package com.cdyykj.system.commons.service;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 通用facade curd 接口
 * Created by Administrator on 2018/2/18.
 */
public interface BaseCrudService<T, ID extends Serializable> {

    /**
     * 分页查询
     *
     * @param map 查询参数
     * @return PageInfo<T>
     */
    PageInfo<T> selectAllPage(Map<String, Object> map) throws RuntimeException;

    /**
     * 查询全部
     *
     * @return
     * @throws RuntimeException
     */
    List<T> selectAllList(Map<String, Object> map) throws RuntimeException;

    /**
     * 根据ids查询所有
     *
     * @param ids 1,2,3,...
     * @return {@code List<T>}
     */
    List<T> selectAllListByIds(String ids) throws RuntimeException;

    /**
     * 根据ids查询所有
     *
     * @param ids [1,2,3,...]
     * @return {@code List<T>}
     */
    List<T> selectAllListByIds(List<String> ids) throws RuntimeException;

    /**
     * 根据ids查询所有
     *
     * @param ids 1,2,3,...
     * @return {@code Map<String ,T>}
     */
    Map<String, T> selectAllMapByIds(String ids) throws RuntimeException;

    /**
     * 根据ids查询有效
     *
     * @param ids [1,2,3,...]
     * @return {@code Map<String ,T>}
     */
    Map<String, T> selectAllMapByIds(List<String> ids) throws RuntimeException;

    T getById(Object id);

    /**
     * 插入
     *
     * @param obj {@link T}
     * @return {@link T}
     */
    int insert(T obj) throws RuntimeException;

    /**
     * 修改
     *
     * @param obj {@link T}
     * @return {@link T}
     */
    int updateById(T obj) throws RuntimeException;

    /**
     * 删除
     *
     * @param id {@link T}的id
     * @return {@link T}
     */
    int delete(Object id) throws RuntimeException;
}