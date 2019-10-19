/**
 * @author kimi
 * @dateTime 2011-11-24 上午10:38:04
 */
package com.cdyykj.system.commons;

import java.io.Serializable;

/**
 * 分页工具类
 * 
 * @author kimi
 * @dateTime 2011-11-24 上午10:38:04
 */
public class PageTools  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 838959624119484L;

	/**
	 * 每页显示的条数
	 * 默认每页显示30条数据
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:38:30
	 */
	private int pageSize = 30;

	/**
	 * 总共的条数
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:45:57
	 */
	private long recordCount;

	/**
	 * 当前页面
	 * 默认当前页为第0页
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:45:59
	 */
	private int currentPage = 0;

	/**
	 * 总页数
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午11:40:41
	 */
//	public int totalPage;

	/**
	 * 默认每页显示多少行
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午11:22:02
	 */
	public static final int pageSizeDefault = 20;

	/**
	 * 默认每页显示多少行
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午11:22:02
	 */
	public static final int currentPageDefault = 0;

	/**
	 * 数据库查询起点
	 * 
	 * @author kimi
	 * @dateTime 2012-6-20 下午3:24:36
	 */
	public int limitFrom;

	/**
	 * 数据库查询起点
	 * 
	 * @author kimi
	 * @dateTime 2012-6-20 下午3:24:39
	 */
	public int limitTo;

	/**
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:45:51
	 * @param pageSize 每页显示多少条数据
	 * @param recordCount 总共有多少条数据
	 * @param currentPage 当前第几页
	 */
	public PageTools(final int pageSize, final long recordCount, final int currentPage) {
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.currentPage = currentPage;
//		setTotalPage(recordCount, pageSize);
	}

	/**
	 * 构造方法
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:45:17
	 * @param recordCount
	 */
	public PageTools(final int recordCount) {
		this(PageTools.pageSizeDefault, recordCount, PageTools.currentPageDefault);
	}
	
	
	/**
	 * 构造方法
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:45:17
	 * @param recordCount
	 */
	public PageTools() {
		this(PageTools.pageSizeDefault, 0, PageTools.currentPageDefault);
	}


	/**
	 * 总页数
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:45:07
	 * @return 返回总页数
	 */
	public long getTotalPage() {
		long size = this.pageSize == 0 ? 0 : (this.recordCount / this.pageSize);// 总条数/每页显示的条数=总页数
		final long mod = this.pageSize == 0 ? 0 : (this.recordCount % this.pageSize);// 最后一页的条数
		if (mod != 0) {
			size++;
		}
		return size;
	}

	/**
	 * 包含，起始索引为0
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:44:48
	 * @return 返回其实索引
	 */
	public int getFromIndex() {
		return (this.currentPage - 1) * this.pageSize;
	}

	/**
	 * 不包含
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:44:42
	 * @return 返回
	 */
	public long getToIndex() {
		return Math.min(this.recordCount, this.currentPage * this.pageSize);
	}

	/**
	 * 得到当前页
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:44:23
	 * @return 当前页
	 */
	public int getCurrentPage() {
		return this.currentPage;
	}

	/**
	 * 设置当前页
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:44:13
	 * @param currentPage
	 */
	public void setCurrentPage(final int currentPage) {
//		int validPage = currentPage <= 0 ? 1 : currentPage;
//		validPage = validPage > this.getTotalPage() ? this.getTotalPage() : validPage;
		this.currentPage = currentPage;
	}

	/**
	 * 得到每页显示的条数
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:43:57
	 * @return 每页显示的行数
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 设置每页显示的条数
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:43:46
	 * @param pageSize
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 得到总共的条数
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:43:29
	 * @return 得到的得到的总条数
	 */
	public long getRecordCount() {
		return this.recordCount;
	}

	/**
	 * 设置总共的条数
	 * 
	 * @author kimi
	 * @dateTime 2011-11-24 上午10:43:06
	 * @param recordCount 设置总共的条数
	 */
	public void setRecordCount(final long recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * 获取查询数据库的开始下标
	 * 如果是第一页，则应该从零开始
	 * 
	 * @author kimi
	 * @dateTime 2012-6-20 下午3:38:47
	 * @return
	 */
	public int getLimitFrom() {
		return (currentPage) * pageSize;
	}

	/**
	 * 获取查询数据库的结束下标
	 * 如果是最后一页，且不够每页显示数量，则有多少查多少
	 * 
	 * @author kimi
	 * @dateTime 2012-6-20 下午3:39:29
	 * @return
	 */
	public long getLimitTo() {
		long totalDataTo = (currentPage+1) * pageSize;
		return recordCount > totalDataTo ? getPageSize() : totalDataTo - (totalDataTo - recordCount);
	}
}
