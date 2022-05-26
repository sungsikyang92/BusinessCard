package kr.or.connect.bCard;

import java.sql.Date;

public class BusinessCard {
	String name;
	String phone;
	String companyName;
	Date createDate;
	public BusinessCard() {
		super();
	}
	@Override
	public String toString() {
		return "businessCard [name=" + name + ", phone=" + phone + ", companyName=" + companyName + ", createDate="
				+ createDate + "]";
	}
	public BusinessCard(String name, String phone, String companyName, Date createDate) {
		super();
		this.name = name;
		this.phone = phone;
		this.companyName = companyName;
		this.createDate = createDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public BusinessCard(String name, String phone, String companyName) {
		super();
		this.name = name;
		this.phone = phone;
		this.companyName = companyName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
