package org.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_04_Get_RestAssured_ValidateResponseBody {

	
	@Test
	public void getRequest() {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		RequestSpecification httprequest = RestAssured.given();
		
		Response response = httprequest.request(Method.GET, "/pondicherry");
		
		JsonPath jsonpathobj = response.jsonPath();
		
		System.out.println(jsonpathobj.get("City"));
		System.out.println(jsonpathobj.get("Temperature"));
		System.out.println(jsonpathobj.get("Humidity"));
		System.out.println(jsonpathobj.get("WeatherDescription"));
		System.out.println(jsonpathobj.get("WindSpeed"));
		System.out.println(jsonpathobj.get("WindDirectionDegree"));
		
		Assert.assertEquals(jsonpathobj.get("City"), "Puducherry");
		Assert.assertEquals(jsonpathobj.get("Temperature"), "32.12 Degree celsius");
		Assert.assertEquals(jsonpathobj.get("Humidity"), "53 Percent");
		Assert.assertEquals(jsonpathobj.get("WeatherDescription"), "clear sky");
		Assert.assertEquals(jsonpathobj.get("WindSpeed"), "5.03 Km per hour");
		Assert.assertEquals(jsonpathobj.get("WindDirectionDegree"), "118 Degree");
}
}