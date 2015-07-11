package com.MutiModule.common.vo;

import java.util.ArrayList;
import java.util.List;


public class EasyUIData {

	private Pagination pagination;
	
	public EasyUIData(Pagination pagination) {
		this.pagination = pagination;
	}

	private int total;
	
	private List rows = new ArrayList();
	
	public int getTotal() {
		return this.pagination.getTotal();
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List getRows() {
		return this.pagination.getData();
	}
	
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
