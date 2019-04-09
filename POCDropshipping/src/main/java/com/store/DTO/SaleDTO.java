package com.store.DTO;

import java.util.ArrayList;

public class SaleDTO {
	private int idClient;
	private int idShippment;
	private ArrayList<int[]> listProducts; 

	// Must have no-argument constructor
	public SaleDTO() {

	}
	
	public SaleDTO(int idClient, ArrayList<int[]> listProducts, int idPaymentMethod) {
		this.idClient = idClient;
		this.listProducts = listProducts;
		this.idShippment = idPaymentMethod;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public ArrayList<int[]> getListProducts() {
		return listProducts;
	}

	public void setListProducts(ArrayList<int[]> listProducts) {
		this.listProducts = listProducts;
	}

	public int getIdShippment() {
		return idShippment;
	}

	public void setIdShippment(int idShippment) {
		this.idShippment = idShippment;
	}

}
