package com.revolut.account.controller;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.revolut.account.dao.InMemoryDao;
import com.revolut.account.dao.InMemoryDaoImpl;
import com.revolut.account.model.Customer;
import com.revolut.account.model.Transfer;
 
@Path("/account")
public class AccountController
{
	InMemoryDao inMemoryDao = new InMemoryDaoImpl(); 
	
    @GET
    @Path("/allCustomers")
    @Produces("application/json")
    public Response findAllCustomers() {
        return Response.status(200).entity(inMemoryDao.listCustomers()).build();
    }
    
    @POST
    @Path("/createCustomer/{email}")
    @Produces("application/json")
    public Response saveCustomerAccount(@PathParam("email") String email) {
    	inMemoryDao.createCustomer(email);
        return Response.status(200).build();
    }
    
    @POST
    @Path("/depositFunds/{email}/{amount}")
    @Produces("application/json")
    public Response depositFunds(@PathParam("email") String email, @PathParam("amount") String amount) {
    	Customer customer = inMemoryDao.findCustomerByEmailId(email);
    	inMemoryDao.depositFunds(customer, new BigDecimal(amount));
        return Response.status(200).build();
    }
    
    
    @POST
    @Path("/transferFunds/{transferrorEmail}/{beneficiaryEmail}/{amount}")
    @Produces("application/json")
    public Response transferFunds(@PathParam("transferrorEmail") String transferrorEmail,
    		@PathParam("beneficiaryEmail") String beneficiaryEmail,
    		@PathParam("amount") String amount) {
    	Transfer transfer = new Transfer(transferrorEmail, beneficiaryEmail, new BigDecimal(amount));
    	inMemoryDao.transferFunds(transfer);
        return Response.status(200).build();
    }
    
    @GET
    @Path("/enquireBalance/{customerEmail}")
    @Produces("application/json")
    public Response enquireBalance(@PathParam("customerEmail") String customerEmail) {
        return Response.status(200).entity(
        		inMemoryDao.enquireBalance(inMemoryDao.findCustomerByEmailId(customerEmail)))
        		.build();
    }
}
