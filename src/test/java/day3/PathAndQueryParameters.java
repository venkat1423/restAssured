package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParameters {
	
	//  https://reqres.in/api/users?page=2&id=5
	
	@Test
	void pathAndQueryParameters() {
		
		given()
			.pathParam("mypath", "users")	// path parameter
			.queryParam("page", 2)			// query parameter
			.queryParam("id", 4)			// query parameter
		
		.when()
			.get("https://reqres.in/api/{mypath}")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
