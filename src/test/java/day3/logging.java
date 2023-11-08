package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class logging {
	
	@Test
	void testLogs() {
		
		given()
		
		.when()
			//.get("https://reqres.in/api/users?page=2")
			.get("http://localhost:3000/students")
			
		.then()
			//.log().cookies();
			//.log().headers();
			//.log().body();
			//.log().status();
			.log().all();
	}
}
