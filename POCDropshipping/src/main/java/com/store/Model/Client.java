package com.store.Model;

import java.util.Date;

public class Client {
	
	private int id;
	private Integer sellerID;
	private String email;
	private String password;
	private String fullName;
	private String cpf;
	private Date registrationDate;
	private String Country;
	private String Phone;
	private String Mobile;
	
	public Client() {};
	
	public Client(Integer sellerID, String email, String password, String fullName, String cpf,
			Date registrationDate, String country, String phone, String mobile) {
		this.sellerID = sellerID;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.cpf = cpf;
		this.registrationDate = registrationDate;
		this.Country = country;
		this.Phone = phone;
		this.Mobile = mobile;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getSellerID() {
		return sellerID;
	}
	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	@Override
	public String toString() {
		return new StringBuffer(" Name : ").append(this.fullName)
				.append(" CPF : ").append(this.cpf)
				.append(" Email : ").append(this.email).append(" ID : ")
				.append(this.id).toString();
	}
}
