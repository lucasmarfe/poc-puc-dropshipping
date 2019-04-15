package com.store.Model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Token {
	
	private int id;
	private String token;
	private Timestamp date;
	private Integer clientID;
	private Integer providerID;
	
	public Token() {}

	public Token(String token) {
		super();
		DateFormat df = new SimpleDateFormat("ddMMyyyyhhmmss");
		Date dat = new Date();
		this.date = new Timestamp(dat.getTime());
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getClientID() {
		return clientID;
	}

	public void setClientID(Integer clientID) {
		this.clientID = clientID;
	}

	public Integer getProviderID() {
		return providerID;
	}

	public void setProviderID(Integer providerID) {
		this.providerID = providerID;
	}
	
	
}
