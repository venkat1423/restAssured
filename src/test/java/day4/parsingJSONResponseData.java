package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;


public class parsingJSONResponseData {
	
	//@Test
	void testJSONResponse() {
		
		//Approach-1
		/*
		given()
			.contentType("Content-Type.JSON")
			
		.when()
			.get("http://localhost:3000/store")
			
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[3].title", equalTo("The Lord of the Rings"));
			*/
		
		//Approach-2
		
		Response res=given()
			.contentType("Content-Type.JSON")
		
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");
	}
	
	@Test
	void testJSONResponseBodyData() {
		
		Response res=given()
			.contentType("Content-Type.JSON")
		
		.when()
			.get("http://localhost:3000/store");
		
		/*
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookname, "The Lord of the Rings");
		*/
		
		//JSON Class
		
		JSONObject jo = new JSONObject(res.asString());	//Converting response to json object
		
		//print all the titles of books
		/*
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
		}
		*/
		
		//Searching the title of the book in json
		
		boolean status=false;
		
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			if(bookTitle.equals("The Lord of the Rings")) {
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
		//validate total price of books
		
		double totalPrice=0;
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			
			totalPrice=totalPrice+Double.parseDouble(price);
		}
		System.out.println("Total price of books is : "+totalPrice);
		
		Assert.assertEquals(totalPrice, 526.0);
	}
}
