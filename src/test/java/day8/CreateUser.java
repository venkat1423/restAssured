package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	@Test
	void testCreateUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject jo = new JSONObject();
		jo.put("name", faker.name().fullName());
		jo.put("gender", "Male");
		jo.put("email", faker.internet().emailAddress());
		jo.put("status", "inactive");
		
		String bearerToken="fd259ac9cb5c78c7049ea452910fe1a878b855cc6b6d06d4883aefcee60040c7";
		
		int id=given()
			.headers("Authorization","Bearer "+bearerToken)
			.contentType("application/json")
			.body(jo.toString())
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		System.out.println("Generated ID is : "+id);
		
		context.getSuite().setAttribute("user_id", id);
		
	}
	
}
