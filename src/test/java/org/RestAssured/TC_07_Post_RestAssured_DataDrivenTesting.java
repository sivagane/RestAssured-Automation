package org.RestAssured;

import java.io.IOException;

import org.RestAssured.Utility.Utility;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_07_Post_RestAssured_DataDrivenTesting {

	@Test(dataProvider="empInfo")
	public void postRequest(String ename, String eage, String esalary) {
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject requestparams= new JSONObject();
		
		requestparams.put("Name", ename);
		requestparams.put("Age", eage);
		requestparams.put("Salary", esalary);
		
		httprequest.header("Content_Type", "application/json");
		
		httprequest.body(requestparams.toJSONString());
		
		Response response = httprequest.request(Method.POST, "/create");
		
	    String responsebody = response.getBody().asString();
	    System.out.println("The Response Body is "+responsebody);
	    
	    Assert.assertEquals(responsebody.contains(ename), true);
	    Assert.assertEquals(responsebody.contains(eage), true);
	    Assert.assertEquals(responsebody.contains(esalary), true);
	    
	    int statuscode = response.statusCode();
	    Assert.assertEquals(statuscode, 200);
	}
	
	@DataProvider(name="empInfo")
	public String[][] getData() throws IOException {
		String path=System.getProperty("user.dir")+"\\src\\main\\java\\org\\RestAssured\\Excel\\Book1.xlsx";
		
		int rowCount = Utility.getRowCount(path, "Sheet1");
		System.out.println(rowCount);
		int cellCount = Utility.getCellCount(path, "Sheet1", 1);
	    System.out.println(cellCount);
		String [][]empData= new String[rowCount][cellCount];
		 
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<cellCount;j++) {
				 empData[i-1][j] = Utility.getCellData(path, "Sheet1", i, j);
				 System.out.println(empData);
			}
		}
	return (empData);
	}
}
