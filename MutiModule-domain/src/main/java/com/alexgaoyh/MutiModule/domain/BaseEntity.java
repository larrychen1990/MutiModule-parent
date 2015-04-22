package com.alexgaoyh.MutiModule.domain;

import java.io.Serializable;

public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 997102449095543774L;
	
	public static final Integer DELETE_NO = 0;
	public static final Integer DELETE_YES = 1;

	/**
	 * 编号，主键标示
	 */
	private Integer id;
	
	/**
	 * 是否删除状态
	 */
	//private DeleteFlagState deleteFlagState = DeleteFlagState.NO;
	private Integer deleteFlagState = DELETE_NO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeleteFlagState() {
		return deleteFlagState;
	}

	public void setDeleteFlagState(Integer deleteFlagState) {
		this.deleteFlagState = deleteFlagState;
	}
	

}
