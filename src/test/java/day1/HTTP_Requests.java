package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

//import io.restassured.module.jsv.JsonSchemaValidator.*;

/*
  given ()
  	content type, set cookies, add auth, add param, set headers info etc....
  
  when ()
  	get, post, put, delete
  
  then ()
  	validate status code, extract response, extract headers, cookies and response body....
  
 */

public class HTTP_Requests {
	
	int id;
	
	@Test(priority = 1) 
	void getUsers() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap hm = new HashMap();
		hm.put("name", "pavan");
		hm.put("job", "trainer");
		
		id=given()
			.contentType("application/json")
			.body(hm)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		/*
		.then()
			.statusCode(201)
			.log().all();
			*/
	}
	
	@Test(priority = 3,dependsOnMethods = {"createUser"})
	void updateUser() {
		
		HashMap data=new HashMap();
		data.put("name", "charan");
		data.put("job", "engineer");
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority = 4)
	void delete() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}
	
}
