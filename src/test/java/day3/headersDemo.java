package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class headersDemo {
	
	//@Test
	void testHeaders() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.statusCode(200)
			.header("Content-Type", equalTo("text/html; charset=ISO-8859-1"))
			.header("Content-Encoding", equalTo("gzip"))
			.header("Server", equalTo("gws"))
			.log().body();
	}
	
	@Test
	void getHeaders() {
		
		Response res=given()
		
		.when()
			.get("https://www.google.com/");
		
		//Get single header value
		String header = res.getHeader("Server");
		System.out.println("The Header Server value is : "+header);
		
		//Get all the header keys and values
		Headers headers = res.getHeaders();
		for (Header h : headers) {
			System.out.println(h.getName()+" : "+h.getValue());
		}
		
	}
	
}
