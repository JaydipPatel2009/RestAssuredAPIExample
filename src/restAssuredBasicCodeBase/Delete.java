package restAssuredBasicCodeBase;

import org.testng.*;
import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;

public class Delete {
	RequestSpecification httpRequest ;
	Response responseData;
	@Parameters({"uri","employeeID"})
	@Test(priority = 1)
	public void setBaseURI(String uri, String employeeID) {
		RestAssured.baseURI = uri+employeeID;
		System.out.println(RestAssured.baseURI);
		httpRequest = RestAssured.given();
	}
	@Parameters({"employeeID"})
	@Test(dependsOnMethods="setBaseURI")
	public void delete(String employeeID)
	{
		responseData = httpRequest.delete();
		System.out.println(responseData.asString());
		System.out.println("exit delete call");
	}
	
	@Test(dependsOnMethods="delete")
	public void verifyStatusCode()
	{
		String s = responseData.jsonPath().get("message");
		System.out.println(s);
	}
}
