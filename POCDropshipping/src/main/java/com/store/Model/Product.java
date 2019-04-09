package com.store.Model;

import java.util.Date;

public class Product {
	
	private int id;
	private Integer providerID;
	private String code;
	private String name;
	private Double price;
	private Double weight;
	private String description;
	private String imagePath;
	private Date updateDate;
	private Double stock;
	private Boolean active;
	
	public Product() {};
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Integer getProviderID() {
		return providerID;
	}



	public void setProviderID(Integer providerID) {
		this.providerID = providerID;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Double getWeight() {
		return weight;
	}



	public void setWeight(Double weight) {
		this.weight = weight;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	public Double getStock() {
		return stock;
	}



	public void setStock(Double stock) {
		this.stock = stock;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	@Override
	public String toString() {
		return new StringBuffer(" Name : ").append(this.name)
				.append(" Stock : ").append(this.stock)
				.append(" Provider : ").append(this.providerID).append(" ID : ")
				.append(this.id).toString();
	}
}
