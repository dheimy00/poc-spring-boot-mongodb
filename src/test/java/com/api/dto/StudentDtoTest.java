package com.api.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.api.model.Address;
import com.api.model.Gender;
import com.api.model.Student;
import com.api.utils.ObjectMapperUtils;

public class StudentDtoTest {
	
	@Test
	@DisplayName("Convert student entity to student DTO")
	public void whenConvertStudentEntityToStudentDto_thenCorrect() {
		
	Student student = new Student();
		
		Address address = new Address();
		address.setCity("Goiania");
		address.setCountry("Brasil");
		address.setState("Goiás");
		address.setPostCode("74630-280");

		student.setFirstName("Dheimy");
		student.setLastName("Silva");
		student.setEmail("teste@gmail.com");
		student.setGender(Gender.MALE);
		student.setAddress(address);
		student.setFavouriteSubjects(List.of("Computer Science"));
		student.setTotalSpentInBooks(BigDecimal.TEN);
		
		StudentDto studentDto = ObjectMapperUtils.map(student, StudentDto.class);
		
		assertThat(studentDto).isNotNull();
		assertEquals(student.getEmail(),studentDto.getEmail());
		
	}
	
	
	@Test
	@DisplayName("Convert student DTO to student entity ")
	public void whenConvertStudentDtoToStudentEntity_thenCorrect() {
		
		StudentDto studentDto = new StudentDto();
		
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("Goiania");
		addressDto.setCountry("Brasil");
		addressDto.setState("Goiás");
		addressDto.setPostCode("74630-280");

		studentDto.setFirstName("Dheimy");
		studentDto.setLastName("Silva");
		studentDto.setEmail("teste@gmail.com");
		studentDto.setGender("Male");
		studentDto.setAddress(addressDto);
		studentDto.setFavouriteSubjects(List.of("Computer Science"));
		studentDto.setTotalSpentInBooks(BigDecimal.TEN);
		
		
		Student student = ObjectMapperUtils.map(studentDto, Student.class);
		
		assertThat(student).isNotNull();
		assertEquals(studentDto.getEmail(),student.getEmail());
		
	}

}
