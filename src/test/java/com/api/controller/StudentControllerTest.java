package com.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.api.builders.StudentBuilder;
import com.api.builders.StudentDtoBuilder;
import com.api.dto.StudentDto;
import com.api.service.StudentService;
import com.api.utils.GeneralMessage;

@ExtendWith(SpringExtension.class)
@DisplayName("Student Controller Test")
public class StudentControllerTest {

	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentService studentService;

	@AfterEach
	public void teardown() {
		RequestContextHolder.resetRequestAttributes();
	}

	@BeforeEach
	public void setUp() {

		BDDMockito.when(studentService.save(any(StudentDto.class))).thenReturn(StudentDtoBuilder.validStudentDto());

		BDDMockito.when(studentService.save(any(StudentDto.class))).thenReturn(StudentDtoBuilder.updateStudentDto());

		BDDMockito.when(studentService.findByFirstName(ArgumentMatchers.anyString()))
				.thenReturn(StudentDtoBuilder.updateStudentDto());

		List<StudentDto> students = Arrays.asList(StudentDtoBuilder.createStudentDto(),
				StudentDtoBuilder.createStudentDto());
		BDDMockito.when(studentService.findAll()).thenReturn(students);

		BDDMockito.when(studentService.findByEmail(ArgumentMatchers.anyString()))
				.thenReturn(StudentDtoBuilder.createStudentDto());

		BDDMockito.doNothing().when(studentService).delete(ArgumentMatchers.anyString());

		HttpServletRequest httpServletRequestMock = new MockHttpServletRequest();
		ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(httpServletRequestMock);
		RequestContextHolder.setRequestAttributes(servletRequestAttributes);

	}

	@Test
	@Order(1)
	@DisplayName("Create student when successfully ")
	public void createStudent_whenSuccessful() {

		String studentID = StudentDtoBuilder.validStudentDto().getId();

		StudentDto studentDto = StudentDtoBuilder.createStudentDto();
		studentDto = studentController.save(studentDto).getBody();

		assertThat(studentDto).isNotNull();
		assertThat(studentDto.getId()).isEqualTo(studentID);
		assertThat(studentDto.getEmail()).isEqualTo(StudentDtoBuilder.validStudentDto().getEmail());

	}

	@Test
	@Order(2)
	@DisplayName("Update student when successfully ")
	public void updateStudent_whenSuccessful() {

		StudentDto studentDto = StudentDtoBuilder.createStudentDto();
		StudentDto updateStudentDto = StudentDtoBuilder.updateStudentDto();

		ResponseEntity<GeneralMessage> responseEntity = studentController.update(studentDto.getFirstName(), updateStudentDto);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	
	@Test
	@Order(3)
	@DisplayName("Find all student when successfully ")
	public void findAllStudent_whenSuccessful() {

		List<StudentDto> studentDtos = studentController.findAll().getBody();

		assertThat(studentDtos).isNotNull();
		assertThat(studentDtos.get(0).getEmail()).isEqualTo(StudentBuilder.validStudent().getEmail());

	}
	
	@Test
	@Order(4)
	@DisplayName("Find firstName student when successfully ")
	public void findByFistNameStudent_whenSuccessful() {

		String firstName = StudentDtoBuilder.createStudentDto().getFirstName();

		StudentDto studentDto = studentController.searchFirstName(firstName).getBody();

		assertThat(studentDto).isNotNull();
		assertThat(studentDto.getFirstName()).isEqualTo(StudentBuilder.validStudent().getFirstName());

	}

	@Test
	@Order(5)
	@DisplayName("Find email student when successfully ")
	public void findByEmailStudent_whenSuccessful() {

		String email = StudentDtoBuilder.createStudentDto().getEmail();
		StudentDto studentDto = studentController.searchEmail(email).getBody();
		
		assertThat(studentDto).isNotNull();
		assertThat(studentDto.getEmail()).isEqualTo(StudentBuilder.validStudent().getEmail());

	}

	@Test
	@Order(6)
	@DisplayName("Delete student when successfully ")
	public void deleteStudent_whenSuccessful() {

		String firstName = StudentDtoBuilder.createStudentDto().getFirstName();

		ResponseEntity<GeneralMessage> responseEntity = studentController.delete(firstName);
				
		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}


}
