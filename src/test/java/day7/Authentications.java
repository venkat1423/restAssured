package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {
	
	//@Test(priority = 1)
	void basicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority = 2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	//@Test(priority = 3)
	void testpreemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	/*
	@Test(priority = 4)
	void testBearerTokenAuthentication() {
		
		String bearerToken="jhfjgh_jgjh3ghg2j";
		
		given()
			.headers("Authentication","Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/user/repos")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	*/
	
	//@Test(priority = 5)
	void testOAuth1Authentication() {
		
		given()
			// This for OAuth1.0 Authentication
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
		
		.when()
			.get("url")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test(priority = 6)
	void testOAuth2Authentication() {
		
		given()
			// This for OAuth1.0 Authentication
			.auth().oauth2("sdfdsdgdr43bf_76sdf")
		
		.when()
			.get("url")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testAPIKeyAuthentication() {
		
		// Method : 1
		/*
		given()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
		
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		
		.then()
			.statusCode(200)
			.log().all();
		*/
		
		given()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
			.pathParam("mypath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
		
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
