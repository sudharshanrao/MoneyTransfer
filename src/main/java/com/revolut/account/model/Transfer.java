package com.revolut.account.model;

import java.math.BigDecimal;

public class Transfer {
	private String fromEmail;
	private String toEmail;
	private BigDecimal amount;
	
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Transfer [fromEmail=" + fromEmail + ", toEmail=" + toEmail + ", amount=" + amount + "]";
	}
	
	

	
	
}
