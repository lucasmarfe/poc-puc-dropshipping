package com.store.Model;

public class OrderDetail {
	private int id;
	private int orderID;
	private int productID;
	private int quantity;
	
	public OrderDetail() {};
	
	
	
	public OrderDetail(int orderID, int productID, int quantity) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return new StringBuffer(" Order : ").append(this.orderID)
				.append(" Product : ").append(this.productID)
				.append(" Quantiry : ").append(this.quantity).append(" ID : ")
				.append(this.id).toString();
	}
}
