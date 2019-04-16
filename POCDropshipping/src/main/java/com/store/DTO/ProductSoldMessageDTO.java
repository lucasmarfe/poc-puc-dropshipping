package com.store.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductSoldMessageDTO {
	private String productCode;
	private String orderCode;
	private String orderDateTime;
	private int quantity;
	private Float unitWeight;
	private String shipFullName;
	private String shipCity;
	private String shipState;
	private String shipZip;
	private String shipPhone;
	private String shipCountry;
	private String shipAddress;
	private String shipAddress2;

	// Must have no-argument constructor
	public ProductSoldMessageDTO() {

	}

	public ProductSoldMessageDTO(String productCode, String orderCode, Date orderDateTime, int quantity, Float unitWeight, String shipFullName, String shipCity,
			String shipState, String shipZip, String shipPhone, String shipCountry, String shipAddress, String shipAddress2) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		this.productCode = productCode;
		this.orderCode = orderCode;
		this.orderDateTime = sdf.format(orderDateTime);
		this.quantity = quantity;
		this.unitWeight = unitWeight;
		this.shipFullName = shipFullName;
		this.shipCity = shipCity;
		this.shipState = shipState;
		this.shipZip = shipZip;
		this.shipPhone = shipPhone;
		this.shipCountry = shipCountry;
		this.shipAddress = shipAddress;
		this.shipAddress2 = shipAddress2;
	}



	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getUnitWeight() {
		return unitWeight;
	}

	public void setUnitWeight(Float unitWeight) {
		this.unitWeight = unitWeight;
	}

	public String getShipFullName() {
		return shipFullName;
	}

	public void setShipFullName(String shipFullName) {
		this.shipFullName = shipFullName;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipState() {
		return shipState;
	}

	public void setShipState(String shipState) {
		this.shipState = shipState;
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}

	public String getShipPhone() {
		return shipPhone;
	}

	public void setShipPhone(String shipPhone) {
		this.shipPhone = shipPhone;
	}

	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipAddress2() {
		return shipAddress2;
	}

	public void setShipAddress2(String shipAddress2) {
		this.shipAddress2 = shipAddress2;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return new StringBuffer(" Order Code : ").append(this.orderCode)
				.append(" DateTime Order : ").append(this.orderDateTime)
				.append(" Quantity : ").append(this.quantity)
				.append(" Weight : ").append(this.unitWeight)
				.append(" Full Name : ").append(this.shipFullName)
				.append(" City : ").append(this.shipCity)
				.append(" State : ").append(this.shipState)
				.append(" ZIP : ").append(this.shipZip)
				.append(" Phone : ").append(this.shipPhone)
				.append(" Country : ").append(this.shipCountry)
				.append(" Address line 1 : ").append(this.shipAddress)
				.append(" Address line 2 : ").append(this.shipAddress2)
				.toString();
	}
	
}
