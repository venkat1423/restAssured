package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day6.Student;

// POJO --- Serialization ---> JSON Object --- Deserialization ---> POJO

public class Serialization_Deserialization {
	
	//@Test
	void convertPojoToJson() throws JsonProcessingException {
		
		// Creating Java Object using Pojo Class
		Student s = new Student();

		s.setName("pruthvi");
		s.setLocation("mulbagal");
		s.setPhone(1876877676);
		String courseArr[]= {"C#","SAP"};
		s.setCourses(courseArr);
		
		// Convert Java object ---> Json object (Serialization)
		ObjectMapper om = new ObjectMapper();
		String jsonData = om.writerWithDefaultPrettyPrinter().writeValueAsString(s);
		System.out.println(jsonData);
	}
	
	@Test
	void convertJsonToPojo() throws JsonMappingException, JsonProcessingException {
		
		String jsondata="{\r\n"
				+ "  \"name\" : \"pruthvi\",\r\n"
				+ "  \"location\" : \"mulbagal\",\r\n"
				+ "  \"phone\" : 1876877676,\r\n"
				+ "  \"courses\" : [ \"C#\", \"SAP\" ]\r\n"
				+ "}";
		
		// Converting Json data ---> Pojo Object (Deserialization)
		
		ObjectMapper mapper = new ObjectMapper();
		Student stu = mapper.readValue(jsondata, Student.class);
		
		System.out.println("Name : "+stu.getName());
		System.out.println("Location : "+stu.getLocation());
		System.out.println("Phone : "+stu.getPhone());
		System.out.println("Course1 : "+stu.getCourses()[0]);
		System.out.println("Course2 : "+stu.getCourses()[1]);
	}
}
