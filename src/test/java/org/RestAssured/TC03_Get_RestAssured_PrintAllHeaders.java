package org.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class TC03_Get_RestAssured_PrintAllHeaders {

	@Test
	public void printAllHeaders() {
		
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET, "/Pondicherry");
		
		String responsebody = response.getBody().asString();
		System.out.println("The Response Body is "+responsebody);
		
		int statuscode = response.getStatusCode();
		System.out.println("The Status Code is "+statuscode);
		Assert.assertEquals(statuscode, 200);
		
		Headers allheaders = response.headers();
		
		for(Header header: allheaders) {
			System.out.println("The Header key -"+header.getName()+"- and the Corresponding Header Value -"+header.getValue());
		}
		
	}
}
