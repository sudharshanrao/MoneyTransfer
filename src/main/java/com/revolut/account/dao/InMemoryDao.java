package com.revolut.account.dao;

import java.util.List;

import com.revolut.account.model.Customer;
import com.revolut.account.model.Transfer;

public interface InMemoryDao {

	void transferFunds(Transfer transfer);
	
	void createCustomer(String email);
	
	Customer findCustomerByEmailId(String email);
	
	List<Customer> listCustomers();
	
}
