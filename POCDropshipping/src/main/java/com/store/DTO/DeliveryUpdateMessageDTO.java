package com.store.DTO;

import java.util.Date;

public class DeliveryUpdateMessageDTO {
	private String trackNumber;
	private String status;
	private String message;
	private Date statusDateTime;
	private String orderCode;
	private int clientId;
	private String productCode;
	private String providerCNPJ;
	

	// Must have no-argument constructor
	public DeliveryUpdateMessageDTO() {

	}

	public DeliveryUpdateMessageDTO(String trackNumber, String status, String message, Date statusDateTime,
			String orderCode, int clientId, String productCode, String providerCNPJ) {
		super();
		this.trackNumber = trackNumber;
		this.status = status;
		this.message = message;
		this.statusDateTime = statusDateTime;
		this.orderCode = orderCode;
		this.clientId = clientId;
		this.productCode = productCode;
		this.providerCNPJ = providerCNPJ;
	}





	public String getTrackNumber() {
		return trackNumber;
	}


	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getStatusDateTime() {
		return statusDateTime;
	}


	public void setStatusDateTime(Date statusDateTime) {
		this.statusDateTime = statusDateTime;
	}


	public String getOrderCode() {
		return orderCode;
	}


	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}


	public int getClientId() {
		return clientId;
	}


	public void setClientId(int clientId) {
		this.clientId = clientId;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getProviderCNPJ() {
		return providerCNPJ;
	}


	public void setProviderCNPJ(String providerCNPJ) {
		this.providerCNPJ = providerCNPJ;
	}
	
}
