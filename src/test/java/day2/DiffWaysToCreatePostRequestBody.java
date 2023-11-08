package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


/*
How many ways we create request body :
-------------------------------------

1) HashMap
2) Using org.json
3) Using POJO (Plain Old Java Object)
4) Using External json file
 */

public class DiffWaysToCreatePostRequestBody {

	//post request using HashMap

	//@Test(priority = 1)
	void postRequestUsingHashMap() {

		HashMap hm=new HashMap();
		hm.put("name", "ullas");
		hm.put("location", "Devanahalli");
		hm.put("phone", "8777756564");

		String courseArray[]= {"ruby","flutter"};
		hm.put("courses", courseArray);

		given()
		.contentType("application/json")
		.body(hm)

		.when()
		.post("http://localhost:3000/students")

		.then()
		.statusCode(201)
		.body("name", equalTo("ullas"))
		.body("location", equalTo("Devanahalli"))
		.body("phone", equalTo("8777756564"))
		.body("courses[0]", equalTo("ruby"))
		.body("courses[1]", equalTo("flutter"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();

	}

	//Post request using org.json

	//@Test(priority = 1)
	void postRequestUsingJsonLibrary() {

		JSONObject jb = new JSONObject();

		jb.put("name", "sharath");
		jb.put("location", "vijaynagar");
		jb.put("phone", "7876648876");

		String courseArr[]= {"B","Node.js"};
		jb.put("courses", courseArr);

		given()
		.contentType("application/json")
		.body(jb.toString())

		.when()
		.post("http://localhost:3000/students")

		.then()
		.statusCode(201)
		.body("name", equalTo("sharath"))
		.body("location", equalTo("vijaynagar"))
		.body("phone", equalTo("7876648876"))
		.body("courses[0]", equalTo("B"))
		.body("courses[1]", equalTo("Node.js"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}

	//Post request using POJO Class

	//@Test(priority = 1)
	void postRequestUsingPOJOClass() {

		POJO_PostRequest pr = new POJO_PostRequest();

		pr.setName("pruthvi");
		pr.setLocation("mulbagal");
		pr.setPhone("8768776766");
		String courseArr[]= {"C#","SAP"};
		pr.setCourses(courseArr);

		given()
		.contentType("application/json")
		.body(pr)

		.when()
		.post("http://localhost:3000/students")

		.then()
		.statusCode(201)
		.body("name", equalTo("pruthvi"))
		.body("location", equalTo("mulbagal"))
		.body("phone", equalTo("8768776766"))
		.body("courses[0]", equalTo("C#"))
		.body("courses[1]", equalTo("SAP"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}

	//Post request using External Json File

	@Test(priority = 1)
	void postRequestUsingExtJsonFile() throws FileNotFoundException {

		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);

		given()
		.contentType("application/json")
		.body(jo.toString())

		.when()
		.post("http://localhost:3000/students")

		.then()
		.statusCode(201)
		.body("name", equalTo("Manu"))
		.body("location", equalTo("Goa"))
		.body("phone", equalTo(123456))
		.body("courses[0]", equalTo("B"))
		.body("courses[1]", equalTo("dot.js"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}

	@Test(priority = 2)
	void deleteRequest() {

		given()

		.when()
		.delete("http://localhost:3000/students/4")
		.then()
		.statusCode(200);
	}
}
