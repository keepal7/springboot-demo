package com.example.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 映射实体
 * @author keepal
 * @date 2018年11月15日
 */
@Entity
@Table(name = "people_t")
public class People extends BaseEntity {

	private static final long serialVersionUID = -6580136785439559680L;

	
	@Id
	@GeneratedValue
	@JsonIgnore //不参与JSON序列化，但是如果直接返回new Gson().toJson() 则不生效
	private long id;//TODO tobe String
	
	@Column(name = "name",nullable = false,length = 16)
	private String name;
	
	@Column(name = "age",nullable = false,length = 16)
	private String age;
	
	@Column(name = "sex",nullable = false,length = 16)
	private String sex;
	
	@Column(name = "tel",nullable = false,length = 16)
	private String tel;
	
	@Column(name = "email",nullable = false,length = 16)
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", tel=" + tel + ", email="
				+ email + "]";
	}
	
	public boolean isLegal(){
		if(name == null||age == null||sex == null||tel == null||email == null){
			return false;
		}
		return true;
	}

}
