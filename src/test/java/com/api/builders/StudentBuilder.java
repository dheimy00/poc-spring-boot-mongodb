package com.api.builders;

import java.math.BigDecimal;
import java.util.List;

import com.api.model.Address;
import com.api.model.Gender;
import com.api.model.Student;

public class StudentBuilder {

	public static Student createStudent() {

		Student student = new Student();
		
		Address address = new Address();
		address.setCity("Goiania");
		address.setCountry("Brasil");
		address.setState("Goiás");
		address.setPostCode("74630-280");

		student.setId("1");
		student.setFirstName("Dheimy");
		student.setLastName("Silva");
		student.setEmail("teste@gmail.com");
		student.setGender(Gender.fromValue("Male"));
		student.setAddress(address);
		student.setFavouriteSubjects(List.of("Computer Science"));
		student.setTotalSpentInBooks(BigDecimal.TEN);

		return student;
	}
	
	public static Student validStudent() {

		Student student = new Student();
		
		Address address = new Address();
		address.setCity("Goiania");
		address.setCountry("Brasil");
		address.setState("Goiás");
		address.setPostCode("74630-280");

		student.setId("1");
		student.setFirstName("Dheimy");
		student.setLastName("Silva");
		student.setEmail("teste@gmail.com");
		student.setGender(Gender.fromValue("Male"));
		student.setAddress(address);
		student.setFavouriteSubjects(List.of("Computer Science"));
		student.setTotalSpentInBooks(BigDecimal.TEN);

		return student;
	}
	
	
	public static Student updateStudent() {

		Student student = new Student();
		
		Address address = new Address();
		address.setCity("Goiania");
		address.setCountry("Brasil");
		address.setState("Goiás");
		address.setPostCode("74630-280");

		student.setId("1");
		student.setFirstName("Dheimy");
		student.setLastName("Santos");
		student.setEmail("teste@gmail.com");
		student.setGender(Gender.fromValue("Male"));
		student.setAddress(address);
		student.setFavouriteSubjects(List.of("Computer Science"));
		student.setTotalSpentInBooks(BigDecimal.TEN);

		return student;
	}
	
	
	
	
}
