package org.RestAssured;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC02_Post_RestAssured {

	@Test
	public void postRequest() {
		
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject requestparams= new JSONObject();
		
		requestparams.put("email", "eve.holt@reqres.in");
		requestparams.put("password", "pistol");
		requestparams.put("username", "sivaganesh");
		requestparams.put("password", "dateofbirth");
	
		httprequest.header("Content-Type", "application/json");
		httprequest.body(requestparams.toJSONString());
		
		Response response = httprequest.request(Method.POST,"/register");
		
		String responsebody = response.getBody().asString();
		System.out.println("The Respone Body of the API is "+responsebody);
		
		int statuscode = response.getStatusCode();
		System.out.println("The Status Code is "+statuscode);
		Assert.assertEquals(statuscode, 200);
		
		 String successcode = response.jsonPath().get("SuccessCode");
		 System.out.println("The Success Code of the API is "+successcode);
		 Assert.assertEquals(successcode, "OPERATION_SUCCESS");		
		
	}
	
	
}
