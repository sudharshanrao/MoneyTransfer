package com.revolut.account.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
	private final String id;
	private final String customerId;
	private BigDecimal currentBalance;
	
	public Account(final String customerId) {
		this.id = UUID.randomUUID().toString();
		this.customerId = customerId;
		this.currentBalance = BigDecimal.ZERO;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getCustomerId() {
		return this.customerId;
	}
	
	public BigDecimal getCurrentBalance() {
		return this.currentBalance;
	}
	
	public void withdrawFunds(BigDecimal amount) {
		if(this.currentBalance.compareTo(amount) == -1) {
			throw new RuntimeException("Withdrawal funds exceed balance !!");
		}
		this.currentBalance = this.currentBalance.subtract(amount);
	}
	
	public void depositFunds(BigDecimal amount) {
		this.currentBalance = this.currentBalance.add(amount);
	}
}
