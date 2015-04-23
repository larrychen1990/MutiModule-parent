package com.alexgaoyh.MutiModule.persist.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 * @author alexgaoyh
 *
 * @param <E>
 */
public class Pagination<E> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5631795318226681173L;

	private int total;
	
	private List<E> data;
	
	private MyRowBounds rowBounds;
	
	public Pagination(MyRowBounds rowBounds, int total) {
		
		this.rowBounds = new MyRowBounds(rowBounds.getOffset(), rowBounds.getLimit(), rowBounds.getPageNumber());
		
		this.total = total;
		
	}
	
	public Pagination(MyRowBounds rowBounds, int total, List<E> data) {
		
		this.rowBounds = new MyRowBounds(rowBounds.getOffset(), rowBounds.getLimit(), rowBounds.getPageNumber());
		
		this.total = total;
		
		this.data = data;
		
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
		if(data != null && data.size() > this.total) {
			this.data = null;
		}
	}

	public MyRowBounds getRowBounds() {
		return rowBounds;
	}

	public void setRowBounds(MyRowBounds rowBounds) {
		this.rowBounds = new MyRowBounds(rowBounds.getOffset(), rowBounds.getLimit(), rowBounds.getPageNumber());
	}
	
	
	/**
	 * 自动以方法，获取页码总数
	 */
	public int getPageTotal() {
		int size = this.rowBounds.getLimit();
		return (int)Math.ceil( ((double)this.total * 1.0D) / (double)size );
	}
}
