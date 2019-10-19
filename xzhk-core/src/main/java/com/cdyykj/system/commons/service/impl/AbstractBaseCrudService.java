package com.cdyykj.system.commons.service.impl;

import com.cdyykj.system.commons.enums.StateEnum;
import com.cdyykj.system.commons.service.BaseCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseCrudService<T, ID extends Serializable> implements BaseCrudService<T,ID> {
	
	
	protected static Logger logger = LoggerFactory.getLogger(AbstractBaseCrudService.class);

    @Autowired
    protected Mapper<T> mapper;

    protected Class<T> tClass;


    public AbstractBaseCrudService() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        tClass = (Class) params[0];
    }

    @Override
    public PageInfo<T> selectAllPage(Map<String, Object> map) throws RuntimeException {
        PageHelper.startPage(Integer.valueOf(String.valueOf(map.get("pageNumber"))), Integer.valueOf(String.valueOf(map.get("pageSize"))));
        Example example = new Example(tClass);
        List<T> list= mapper.selectByExample(example);
        System.out.println(list);
        return new PageInfo<>(list);
    }

    @Override
    public List<T> selectAllList(Map<String,Object> map) throws RuntimeException {
        return mapper.selectAll();
    }

    private List<T> selectAllListByIds(String ids, StateEnum state) throws RuntimeException {
        Example example = new Example(tClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids.split(",")));
        if (state != null) {
            criteria.andEqualTo("state",state.getState());
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectAllListByIds(String ids) throws RuntimeException {
        return selectAllListByIds(ids,null);
    }

    private List<T> selectAllListByIds(List<String> ids, StateEnum state) throws RuntimeException {
        Example example = new Example(tClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        if (state != null) {
            criteria.andEqualTo("state",state.getState());
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectAllListByIds(List<String> ids) throws RuntimeException {
        return selectAllListByIds(ids,null);
    }

    @Override
    public Map<String, T> selectAllMapByIds(String ids) throws RuntimeException {
        return null;
    }

    @Override
    public Map<String, T> selectAllMapByIds(List<String> ids) throws RuntimeException {
        return null;
    }

    @Override
    public T getById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(T t) throws RuntimeException {
        return mapper.insertSelective(t);
    }
    
    @Override
    public int updateById(T t) throws RuntimeException {
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int delete(Object id) throws RuntimeException {
        return mapper.deleteByPrimaryKey(id);
    }
}
