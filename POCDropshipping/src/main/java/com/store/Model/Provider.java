package com.store.Model;

import java.util.Date;

public class Provider {
	
	private int id;
	private String name;
	private String city;
	private String state;
	private String zip;
	private String email;
	private Date registrationDate;
	private String phone;
	private String fax;
	private String country;
	private String address;
	private String address2;
	private String CNPJ;
	private String topicName_Orders;
	private String topicName_Shipping;
	
	public Provider() {};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getTopicName_Orders() {
		return topicName_Orders;
	}

	public void setTopicName_Orders(String topicName_Orders) {
		this.topicName_Orders = topicName_Orders;
	}

	public String getTopicName_Shipping() {
		return topicName_Shipping;
	}

	public void setTopicName_Shipping(String topicName_Shipping) {
		this.topicName_Shipping = topicName_Shipping;
	}

	@Override
	public String toString() {
		return new StringBuffer(" Name : ").append(this.name)
				.append(" CNPJ : ").append(this.CNPJ)
				.append(" Email : ").append(this.email).append(" ID : ")
				.append(this.id).toString();
	}
}
