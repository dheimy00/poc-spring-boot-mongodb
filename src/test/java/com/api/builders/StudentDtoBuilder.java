package com.api.builders;

import java.math.BigDecimal;
import java.util.List;

import com.api.dto.AddressDto;
import com.api.dto.StudentDto;

public class StudentDtoBuilder {
	
	private StudentDto studentDto;

	public static StudentDto createStudentDto() {

		StudentDto studentDto = new StudentDto();
		
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("Goiania");
		addressDto.setCountry("Brasil");
		addressDto.setState("Goiás");
		addressDto.setPostCode("74630-280");
		studentDto.setId("1");
		studentDto.setFirstName("Dheimy");
		studentDto.setLastName("Silva");
		studentDto.setEmail("teste@gmail.com");
		studentDto.setGender("Male");
		studentDto.setAddress(addressDto);
		studentDto.setFavouriteSubjects(List.of("Computer Science"));
		studentDto.setTotalSpentInBooks(BigDecimal.TEN);

		return studentDto;
	}
	
	public static StudentDto validStudentDto() {

		StudentDto studentDto = new StudentDto();
		
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("Goiania");
		addressDto.setCountry("Brasil");
		addressDto.setState("Goiás");
		addressDto.setPostCode("74630-280");
		studentDto.setId("1");
		studentDto.setFirstName("Dheimy");
		studentDto.setLastName("Silva");
		studentDto.setEmail("teste@gmail.com");
		studentDto.setGender("Male");
		studentDto.setAddress(addressDto);
		studentDto.setFavouriteSubjects(List.of("Computer Science"));
		studentDto.setTotalSpentInBooks(BigDecimal.TEN);

		return studentDto;
	}
	
	public static StudentDto updateStudentDto() {

		StudentDto studentDto = new StudentDto();
		
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("Goiania");
		addressDto.setCountry("Brasil");
		addressDto.setState("Goiás");
		addressDto.setPostCode("74630-280");
		studentDto.setId("1");
		studentDto.setFirstName("Dheimy");
		studentDto.setLastName("Santos");
		studentDto.setEmail("teste@gmail.com");
		studentDto.setGender("Male");
		studentDto.setAddress(addressDto);
		studentDto.setFavouriteSubjects(List.of("Computer Science"));
		studentDto.setTotalSpentInBooks(BigDecimal.TEN);

		return studentDto;
	}
	
}
