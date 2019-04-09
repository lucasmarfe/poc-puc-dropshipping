package com.store.Model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Order {
	@Id
	@GeneratedValue
	private int id;
	private int clientID;
	private int shipAddressID;
	private String code;
	private Timestamp  date;
	
	public Order() {};
	
	public Order(int clientId, int shipAddressId) {
		DateFormat df = new SimpleDateFormat("ddMMyyyyhhmmss");
		Date dat = new Date();
		this.clientID = clientId;
		this.shipAddressID = shipAddressId;
		this.code = String.valueOf(clientId) + String.valueOf(shipAddressId) + df.format(dat);
		this.date = new Timestamp(dat.getTime());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientID() {
		return clientID;
	}



	public void setClientID(int clientID) {
		this.clientID = clientID;
	}



	public int getShipAddressID() {
		return shipAddressID;
	}



	public void setShipAddressID(int shipAddressID) {
		this.shipAddressID = shipAddressID;
	}



	public Timestamp getDate() {
		return date;
	}



	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return new StringBuffer(" Client : ").append(this.clientID)
				.append(" Ship : ").append(this.shipAddressID)
				.append(" Date : ").append(this.date).append(" ID : ")
				.append(this.id).toString();
	}
}
