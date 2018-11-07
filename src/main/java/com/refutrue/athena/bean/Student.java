package com.refutrue.athena.bean;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.refutrue.athena.utils.base.BaseBean;
import com.refutrue.athena.utils.template.annotation.Calender;
import com.refutrue.athena.utils.template.annotation.Column;
import com.refutrue.athena.utils.template.annotation.Ignore;
import com.refutrue.athena.utils.template.annotation.Title;
import com.refutrue.athena.utils.template.annotation.Validate;

@Title("学生信息")
public class Student extends BaseBean implements Serializable{
	
	@Ignore
	private static final long serialVersionUID = 4040484417229617858L;

	@Title("姓名")
	@Validate(required=true,length=30)
	private String name;
	
	@Title("年龄")
	@Validate(required=true,maxNum=200,minNum=1)
	private Integer age;
	
	@Title("性别")
	private Boolean gender;
	
	@Title("生日")
	@Column(columnType="DATE")
	@Calender(javaFormatter="yyyy-MM-dd")
	@Validate(required=true,startTime="1900-01-01 00:00:00",endTime="now")
	private Date birthDay;
	
	@Title("住址")
	@Column(columnType="VARCHAR(200)")
	private String address;
	
	@Title("国家")
	private String country;
	
	@Title("爱好")
	@Column(columnType="VARCHAR(200)")
	private String hobby;
	
	private Integer gradeId;

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

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	public static void main(String[] args) {
		Student student = new Student();
		student.setName("小冬芮");
		student.setAge(28);
		student.setCountry("CN");
		student.setGender(true);
		student.setBirthDay(new Date());
		System.out.println(JSON.toJSON(student));
		
	}
	
}
