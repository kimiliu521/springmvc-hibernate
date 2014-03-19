package com.kimi.forum.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_PAGE_SIZE = 20;
	
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	private long start;
	
	private List data;
	
	private Long totalCount;
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}
	public Page(long start, long totalSize, int pageSize, List data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}
	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}
	
	/**
	 * 取总页数.
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}
	
	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 取当前页中的记录.
	 */
	public List getResult() {
		return data;
	}
	
	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}
	
	/**
	 * 该页是否有下一页.
	 */
	public boolean isHasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}
	
	/**
	 * 该页是否有上一页.
	 */
	public boolean isHasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 *
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
}
