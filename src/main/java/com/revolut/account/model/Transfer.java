package com.revolut.account.model;

import java.math.BigDecimal;

public class Transfer {
	private String fromEmail;
	private String toEmail;
	private BigDecimal amount;
	
	public Transfer(String fromEmail, String toEmail, BigDecimal amount) {
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.amount = amount;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public String getToEmail() {
		return toEmail;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		return "Transfer [fromEmail=" + fromEmail + ", toEmail=" + toEmail + ", amount=" + amount + "]";
	}
}
