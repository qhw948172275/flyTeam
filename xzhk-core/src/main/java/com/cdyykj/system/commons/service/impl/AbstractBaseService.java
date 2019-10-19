package com.cdyykj.system.commons.service.impl;

import com.cdyykj.system.commons.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: AbstractBaseService 
* @author zhaoqiang 
* @date 2017年11月8日 下午3:07:52 
* 
* @param <T>
* @param <ID>
 */
public class AbstractBaseService<T, ID extends Serializable> implements BaseService<T, ID> {
	
	 @Autowired
	 protected Mapper<T> mapper;

	@Override
	public int deleteByPrimaryKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int delete(T record) {
		return mapper.delete(record);
	}

	@Override
	public int insert(T record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return mapper.insertSelective(record);
	}

	@Override
	public List<T> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public T selectByPrimaryKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int selectCount(T record) {
		return mapper.selectCount(record);
	}

	@Override
	public List<T> select(T record) {
		return mapper.select(record);
	}

	@Override
	public T selectOne(T record) {
		return mapper.selectOne(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

}
