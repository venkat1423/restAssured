package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponseData {
	
	//@Test
	void testXMLResponse() {
		
		//Approach-1
		/*
		given()
			
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
			
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[2].name", equalTo("vano"));
		*/
		
		//Approach-2
		
		Response res=given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[2].name").toString();
		Assert.assertEquals(travelerName, "vano");
	}
	
	@Test
	void testXMLResponseBody() {
		
		Response res=given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlObj = new XmlPath(res.asString());
		
		//Verify the total number of travelers in the particular page
		List<String> travelers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travelers.size(), 10);
		
		//Verify traveler Name is present in response
		List<String> travelerNames = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean status=false;
		for (String travelerName : travelerNames) {
			//System.out.println(travelerName);
			if(travelerName.equals("vano")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
	
}
