package com.revolut.account;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.account.controller.AccountController;

public class RevolutMoneyTransferApp extends Application
{
	private static final Logger logger = LoggerFactory.getLogger(RevolutMoneyTransferApp.class);
	
	@Override
    public Set<Object> getSingletons() {
        final Set<Object> set = new HashSet<>();
        set.add(new AccountController());
        return set;
    }

}
