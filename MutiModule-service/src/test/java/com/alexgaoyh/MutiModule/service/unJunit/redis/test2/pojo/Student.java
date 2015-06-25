package com.alexgaoyh.MutiModule.service.unJunit.redis.test2.pojo;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{

	private String name;
	
	private Integer age;
	
	private String schoolName;
	
	private Date birthDay;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	private String addedColumn;

	public String getAddedColumn() {
		return addedColumn;
	}

	public void setAddedColumn(String addedColumn) {
		this.addedColumn = addedColumn;
	}
	
	
}
