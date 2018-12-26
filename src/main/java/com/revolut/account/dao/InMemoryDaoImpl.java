package com.revolut.account.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.revolut.account.model.Account;
import com.revolut.account.model.Customer;
import com.revolut.account.model.Transfer;

public class InMemoryDaoImpl implements InMemoryDao{

	private final Map<String, Customer> customerMap;
	private final Map<String, Account> accountMap;
	
    public InMemoryDaoImpl() {
    	this.customerMap = new ConcurrentHashMap<>();
        this.accountMap = new ConcurrentHashMap<>();
    }
    
    // create custoer and corresponding account. 
    public synchronized void createCustomer(String email) {
    	if(customerMap.containsKey(email)) {
    		throw new RuntimeException("This email has already been used to register a customer !!");
    	}
    	Customer customer = new Customer(email);
        customerMap.put(email, customer);
        Account account = new Account(customer.getId());
        accountMap.put(account.getCustomerId(), account);
    }
    
    public Customer findCustomerByEmailId(String email) {
    	if(customerMap.containsKey(email)) {
    		return customerMap.get(email);
    	}
        throw new RuntimeException("The email: "+email+" does not exist in database !!");
    }

    // return a list of customers. if size is more then we can think of returning a stream.
    public List<Customer> listCustomers() {
        return customerMap.values()
                      .stream().collect(Collectors.toList());
    }
	
    // assumes that both accounts are same currency
	public synchronized void transferFunds(Transfer transfer) {
		Customer transferror = findCustomerByEmailId(transfer.getFromEmail());
		Account transferrorAccount = findAccountByCustomerId(transferror.getId());
		Customer beneficiary = findCustomerByEmailId(transfer.getToEmail());
		Account beneficiaryAccount = findAccountByCustomerId(beneficiary.getId());
		transferrorAccount.withdrawFunds(transfer.getAmount());
		beneficiaryAccount.depositFunds(transfer.getAmount());
	}

	public Account findAccountByCustomerId(String customerId) {
		if(accountMap.containsKey(customerId)) {
			return accountMap.get(customerId);
		}
		throw new RuntimeException("CustomerId: "+customerId+" not found in database !!");
	}
	
	public synchronized void depositFunds(Customer beneficiary, BigDecimal amount) {
		Account beneficiaryAccount = findAccountByCustomerId(beneficiary.getId());
		beneficiaryAccount.depositFunds(amount);
	}
	
	public Account enquireBalance(Customer customer) {
		return findAccountByCustomerId(customer.getId());
	}
}