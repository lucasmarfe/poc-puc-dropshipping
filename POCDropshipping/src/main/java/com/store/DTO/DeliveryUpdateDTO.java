package com.store.DTO;

import java.util.Date;

public class DeliveryUpdateDTO {
	private String productCode;
	private String orderCode;
	private Date statusDateTime;
	private String status;
	private String trackNumber;
	private String message;

	// Must have no-argument constructor
	public DeliveryUpdateDTO() {
		
	}
	
	

	public DeliveryUpdateDTO(String productCode, String orderCode, Date statusDateTime, String status,
			String trackNumber, String message) {
		super();
		this.productCode = productCode;
		this.orderCode = orderCode;
		this.statusDateTime = statusDateTime;
		this.status = status;
		this.trackNumber = trackNumber;
		this.message = message;
	}



	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getStatusDateTime() {
		return statusDateTime;
	}

	public void setStatusDateTime(Date statusDateTime) {
		this.statusDateTime = statusDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
