package com.alexgaoyh.MutiModule.domain;

import java.io.Serializable;

import com.alexgaoyh.MutiModule.domain.enumState.DeleteFlagState;

public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 997102449095543774L;

	/**
	 * 编号，主键标示
	 */
	private Integer id;
	
	/**
	 * 是否删除状态
	 */
	private DeleteFlagState deleteFlagState = DeleteFlagState.NO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DeleteFlagState getDeleteFlagState() {
		return deleteFlagState;
	}

	public void setDeleteFlagState(DeleteFlagState deleteFlagState) {
		this.deleteFlagState = deleteFlagState;
	}
}
