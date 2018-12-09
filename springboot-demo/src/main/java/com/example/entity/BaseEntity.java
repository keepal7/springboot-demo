package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公共父类 用@MappedSuperclass 标识 不得再用@Entity或@Table注解
 * 
 * @author keepal
 * @date 2018年11月15日
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 6984922780747771677L;

	//使用注解格式化日期
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", locale = "zh", timezone = "GMT+8")
	private Date createDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", locale = "zh", timezone = "GMT+8")
	private Date updateDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "BaseEntity [createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
