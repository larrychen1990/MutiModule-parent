package com.MutiModule.common.vo;

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
	
	public Pagination(int total, List<E> data) {
		
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
}
