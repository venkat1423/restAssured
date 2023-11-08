package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void testUpdateUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject jo = new JSONObject();
		jo.put("name", faker.name().fullName());
		jo.put("gender", "Male");
		jo.put("email", faker.internet().emailAddress());
		jo.put("status", "active");
		
		String bearerToken="fd259ac9cb5c78c7049ea452910fe1a878b855cc6b6d06d4883aefcee60040c7";
		
		int id=(Integer) context.getSuite().getAttribute("user_id");
		
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.pathParam("id", id)
			.body(jo.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
}
