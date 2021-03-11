package restAssuredBasicCodeBase;

import org.testng.*;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;

public class Get {
	RequestSpecification httpRequest ;
	Response responseData;
	@Test(dataProvider="uri", priority = 1)
	public void setBaseURI(String operation, String uri) {
		RestAssured.baseURI = uri;
		httpRequest = RestAssured.given();
		responseData = httpRequest.get();
	}
	
	@Test(dependsOnMethods="setBaseURI")
	public void verifyStatusCode()
	{
		Assert.assertEquals(httpRequest.get().getStatusCode(),200);
	}
	
	@Parameters({"employeeName"})
	@Test(dependsOnMethods="verifyStatusCode")
	public void getDetails(String employeeName) {
		List<Map<String, String>> jsonResponse = responseData.jsonPath().getList("data");
		for(Map<?, ?> obj: jsonResponse)
		{
			if(obj.get("employee_name").equals(employeeName)) {
				System.out.println(obj);
			}
		}
	}
	
	@DataProvider
	public Object [][] uri(){
		return new Object[][] {{"GET","http://dummy.restapiexample.com/api/v1/employees"}};
	}
}
