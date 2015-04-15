package com.alexgaoyh.MutiModule.domain.enumState;

public enum DeleteFlagState {
	
	YES(0,"已删除"), NO(1,"未删除");
	
	private Integer code;
	private String name;
	
	private DeleteFlagState(Integer code, String name){
		this.code = code;
		this.name = name;
	}
	
	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
