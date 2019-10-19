package com.cdyykj.system.commons;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: BaseService 
* @author zhaoqiang 
* @date 2017年11月8日 下午3:07:42 
* 
* @param <T>
* @param <ID>
 */
public interface BaseService<T,ID extends Serializable> {
	
	int deleteByPrimaryKey(Object key);
	int delete(T record);
	
	int insert(T record);
	int insertSelective(T record);
	
	List<T> selectAll();
	T selectByPrimaryKey(Object key);
	int selectCount(T record);
	List<T> select(T record);
	T selectOne(T record);
	
	int updateByPrimaryKey(T record);
	int updateByPrimaryKeySelective(T record);
	
}
