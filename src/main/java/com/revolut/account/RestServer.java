package com.revolut.account;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import io.undertow.Undertow;

public class RestServer {
	private static final Logger logger = Logger.getLogger(RestServer.class.getName());
	public static void main(String[] args){
    	int port = 8080;
        String host = "0.0.0.0";

        UndertowJaxrsServer server = new UndertowJaxrsServer();
        RevolutMoneyTransferApp app = new RevolutMoneyTransferApp();
        server.deploy(app);
        logger.debug("starting on http://" + host + ":" + port);
        server.start(Undertow.builder()
                .addHttpListener(port, host)
                );
    }
}
