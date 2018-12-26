package com.revolut.moneyTransfer;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class MoneyTransferTest 
{
	
	@BeforeClass
	public static void setup() {
	    RestAssured.baseURI = "http://localhost";
	    RestAssured.port = 8080;
	    RestAssured.defaultParser = Parser.JSON;
	    
	}
	
    @Test
    public void whenRequestListCustomers_thenOK(){
        given().when().get("/account/allCustomers").then().statusCode(200);
    }

    @Test
    public void whenRequestCreateCustomerAndTransferFunds_thenOK(){
    	JsonPath.config = new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL);
    	given()
        .contentType("application/json")
        .when().post("/account/createCustomer/sudharshanrao@gmail.com").then()
        .statusCode(200);
    	
    	given()
        .contentType("application/json")
        .when().post("/account/depositFunds/sudharshanrao@gmail.com/20").then()
        .statusCode(200);
    	
    	given()
        .contentType("application/json")
        .when().post("/account/createCustomer/rao_sudharshan@yahoo.com").then()
        .statusCode(200);

    	given()
        .contentType("application/json")
        .when().post("/account/transferFunds/sudharshanrao@gmail.com/rao_sudharshan@yahoo.com/5").then()
        .statusCode(200);

    	given()
        .contentType("application/json")
        .when().get("/account/enquireBalance/sudharshanrao@gmail.com").then()
        .body("currentBalance",equalTo(15));
    
    	given()
        .contentType("application/json")
        .when().get("/account/enquireBalance/rao_sudharshan@yahoo.com").then()
        .body("currentBalance",equalTo(5));
    }

}
