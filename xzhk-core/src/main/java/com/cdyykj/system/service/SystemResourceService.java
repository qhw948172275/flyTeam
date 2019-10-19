package com.cdyykj.system.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cdyykj.system.commons.MyPageInfo;
import com.cdyykj.system.commons.PageTools;
import com.cdyykj.system.entity.SystemResource;

import java.util.List;

/**
 * 资源文件管理接口
 * 
 * @author kimi
 * @dateTime 2012-3-20 上午10:39:37
 */
public interface SystemResourceService {

	/**
	 * 根据条件获取资源信息
	 * 
	 * @author kimi
	 * @dateTime 2012-3-20 上午10:39:40
	 * @return
	 */
	long getResourceCounts(String resourceName);

	/**
	 * 根据条件获取资源信息
	 * 
	 * @author kimi
	 * @dateTime 2012-3-20 上午10:39:40
	 * @return
	 */
	MyPageInfo<SystemResource> getResourceList(String resourceName, PageTools page);
	/**
	 * 根据条件获取资源信息
	 * 
	 * @author kimi
	 * @dateTime 2012-3-20 上午10:39:40
	 * @return
	 */
	List<SystemResource> getResourceList(String resourceName, int status, PageTools page);

	/**
	 * 添加资源信息
	 *
	 * @author kimi
	 * @dateTime 2012-3-20 下午8:23:49
	 * @param re
	 * @return
	 */
	boolean addResource(SystemResource re);

	/**
	 * 根据ID，删除资源信息
	 *
	 * @author kimi
	 * @dateTime 2012-3-20 下午8:24:57
	 * @param resourceId
	 * @return
	 */
	boolean deleResource(int resourceId);

	/**
	 * 更新资源信息
	 *
	 * @author kimi
	 * @dateTime 2012-3-20 下午8:24:59
	 * @param re
	 * @return
	 */
	boolean updateResource(SystemResource re);

	/**
	 * 根据ID，获取资源信息
	 *
	 * @author kimi
	 * @dateTime 2012-3-20 下午8:25:02
	 * @return
	 */
	SystemResource getResourceById(int id);


	/**
	 * 根据资源的级别查询资源信息
	 *
	 * @author kimi
	 * @dateTime 2012-3-21 下午12:11:34
	 * @param level
	 * @return
	 */
	List<SystemResource> getParentResourceByLevel(int level);

	/**
	 * 验证资源名称是否存在
	 *
	 * @author kimi
	 * @dateTime 2012-3-21 下午2:54:10
	 * @param resourceName
	 * @return
	 */
	boolean validateResourceName(String resourceName);

	/**
	 * 验证资源文件是否存在
	 *
	 * @author kimi
	 * @dateTime 2012-3-21 下午2:54:07
	 * @param resourceUrl
	 * @return
	 */
	boolean validateResourceUrl(String resourceUrl);

	List<SystemResource> getResourceListByUserId(int uid);



	/**
	 * 根据资源的子系统编号查询资源信息
	 *
	 * @author hanfeng
	 * @dateTime 2018-2-27 下午17:20:34
	 * @param resourceKind 子系统编号
	 * @return
	 */
	List<SystemResource> getParentResourceByResourceKind(Integer resourceKind);

	/**
	 * 根据资源的子系统编号查询资源信息,将排除id
	 *
	 * @author hanfeng
	 * @dateTime 2018-2-27 下午17:20:34
	 * @param resourceKind 子系统编号
	 * @return
	 */
	List<SystemResource> getParentResourceByResourceKind(Integer id, Integer resourceKind);

	/**
	 * 通过父级路径查询所有子集
	 * @param parentId
	 * @return
	 */
	List<SystemResource> getSubResourceByParentId(Integer parentId);

	/**
	 * 通过父级路径查询所有子集,不包含按钮
	 * @param parentId
	 * @return
	 */
	List<SystemResource> getSubResourceNotBtnByParentId(Integer parentId);

	/**
	 * 通过父级路径查询当前子集
	 * @param parentId
	 * @return
	 */
	List<SystemResource> getCurSubResourceByParentId(Integer parentId);

	/**
	 * 构建新增字段数据
	 * @throws JSONException 
	 */
	void buildParentPath() throws JSONException;

	/**
	 * 查询页面菜单树数据结构
	 * @param parentId
	 * @return
	 * @throws JSONException 
	 */
	List<JSONObject> selectSubNavbar(Integer parentId) throws JSONException;



}
