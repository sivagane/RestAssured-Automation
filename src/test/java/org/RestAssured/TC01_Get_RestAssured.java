package org.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_Get_RestAssured {

	@Test
	public void getRequest() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET, "/Hyderabad");
		
		String responsebody = response.getBody().asString();
		System.out.println("The Response Body is "+responsebody);
		
		int statuscode = response.getStatusCode();
		System.out.println("The Status Code is "+statuscode);
		Assert.assertEquals(statuscode, 200);
		
		String statusline = response.getStatusLine();
		System.out.println("The Status Line is "+statusline);
//		Assert.assertEquals(statusline, "");
	}
	
}
