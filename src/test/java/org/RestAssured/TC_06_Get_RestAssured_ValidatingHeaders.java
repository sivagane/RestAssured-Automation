package org.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_06_Get_RestAssured_ValidatingHeaders {
	@Test
	public void getRequest() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET, "/Pondicherry");
		
		String responsebody = response.getBody().asString();
		System.out.println("The Response Body is "+responsebody);
		
		String contentencoding = response.getHeader("content-encoding");
		System.out.println(contentencoding);
		Assert.assertEquals(contentencoding, "gzip");
		
		String Server = response.getHeader("server");
		System.out.println(Server);
		Assert.assertEquals(Server, "nginx");
		
		int statuscode = response.getStatusCode();
		System.out.println("The Status Code is "+statuscode);
		Assert.assertEquals(statuscode, 200);
}
}