package com.revolut.account;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.account.controller.AccountController;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class RevolutMoneyTransferApp extends Application
{
	private static final Logger logger = LoggerFactory.getLogger(RevolutMoneyTransferApp.class);
	public static void moneyTransferHandler(HttpServerExchange exchange) {
	    exchange.getResponseHeaders().add(Headers.CONTENT_TYPE, 
	    		"text/plain");
	    exchange.getResponseSender().send("Hello World!");
	}
	
	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> clazzes = new HashSet<Class<?>>();
        clazzes.add(AccountController.class);
        return clazzes;
    }
}
