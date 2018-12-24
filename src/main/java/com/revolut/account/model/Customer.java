package com.revolut.account.model;

import java.util.UUID;

public class Customer {
	
	private String id;
	private String email;
	
	public Customer(String email) {
		this.id = UUID.randomUUID().toString();
		this.email = email;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
}