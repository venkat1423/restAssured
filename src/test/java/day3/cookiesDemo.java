package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class cookiesDemo {
	
	//@Test
	void testCookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
			
		.then()
			.log().all();
	}
	
	@Test
	void testCookiesValue() {
		
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		//Get single cookie value
		
		//String cookie_value = res.getCookie("AEC");
		//System.out.println("Cookie value is : "+cookie_value);
		
		//Get all cookies values
		
		Map<String, String> cookies_value = res.getCookies();
		//System.out.println("Cookies keys are : "+cookies_value.keySet());
		
		for(String k:cookies_value.keySet()) {
			String cookie_value=res.getCookie(k);
			System.out.println(k+" = "+cookie_value);
		}
			
	}
}
