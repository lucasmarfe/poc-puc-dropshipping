package com.store.Model;

import java.util.Date;

public class ShipAddress {
	
	private int id;
	private Integer clientID;
	private String fullName;
	private String city;
	private String state;
	private String zip;
	private Date registrationDate;
	private String phone;
	private String country;
	private String address;
	private String address2;
	private Boolean active;
	
	public ShipAddress() {};
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Integer getClientID() {
		return clientID;
	}


	public void setClientID(Integer clientID) {
		this.clientID = clientID;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
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


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	@Override
	public String toString() {
		return new StringBuffer(" Name : ").append(this.fullName)
				.append(" Address : ").append(this.address)
				.append(" City : ").append(this.city).append(" ID : ")
				.append(this.id).toString();
	}
}
