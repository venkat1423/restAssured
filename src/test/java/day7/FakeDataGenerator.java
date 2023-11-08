package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakeDataGenerator {
	
	@Test
	void testGenerateDummyData() {
		
		Faker f = new Faker();
		String fullName = f.name().fullName();
		String firstName = f.name().firstName();
		String lastName = f.name().lastName();
		String username = f.name().username();
		String emailAddress = f.internet().emailAddress();
		String password = f.internet().password();
		String phoneNumber = f.phoneNumber().cellPhone();
		
		System.out.println("fullname : "+fullName);
		System.out.println("firstname : "+firstName);
		System.out.println("lastname : "+lastName);
		System.out.println("username : "+username);
		System.out.println("emailAddress : "+emailAddress);
		System.out.println("password : "+password);
		System.out.println("phoneNumber : "+phoneNumber);
		
	}
	
}
