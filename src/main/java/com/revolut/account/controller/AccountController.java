package com.revolut.account.controller;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.ws.spi.http.HttpExchange;
import javax.xml.ws.spi.http.HttpHandler;

import com.revolut.account.dao.InMemoryDao;
import com.revolut.account.dao.InMemoryDaoImpl;
 
@Path("/account")
public class AccountController extends HttpHandler
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

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		
	}
    
}
