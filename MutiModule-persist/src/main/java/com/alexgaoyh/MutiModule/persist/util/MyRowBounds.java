package com.alexgaoyh.MutiModule.persist.util;

import java.io.Serializable;

/**
 *  与  org.apache.ibatis.session.RowBounds 类似，添加了pageNumber属性
 *  
 * @author alexgaoyh
 *
 */
public class MyRowBounds implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2860692762946249134L;
	
	public final static int NO_ROW_OFFSET = 0;
	public final static int NO_ROW_LIMIT = Integer.MAX_VALUE;
	public final static MyRowBounds DEFAULT = new MyRowBounds();
	
	public final static int NO_PAGENUMBER = 0;

	//偏移量
	private int offset;
	//条数
	private int limit;
	//页码
	private int pageNumber;

	public MyRowBounds() {
		this.offset = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
		this.pageNumber = NO_PAGENUMBER;
	}

	public MyRowBounds(int offset, int limit, int pageNumber) {
	    this.offset = offset;
	    this.limit = limit;
	    this.pageNumber = pageNumber;
	}
	
	/**
	 * 返回RowBounds实体信息
	 * @param pageNumber 页码
	 * @param pageSize 一页条数
	 */
	public MyRowBounds(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.limit = pageSize;
		this.offset = (pageNumber - 1) * pageSize;
	}
	

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
