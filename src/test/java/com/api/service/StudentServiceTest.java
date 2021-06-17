package com.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.api.builders.StudentBuilder;
import com.api.builders.StudentDtoBuilder;
import com.api.dto.StudentDto;
import com.api.exceptions.general.ResourceNotFoundException;
import com.api.model.Student;
import com.api.repository.StudentRepository;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Student Service Test")
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceImpl studentService;

	@BeforeEach
	public void setUp() {

		BDDMockito.when(studentRepository.insert(any(Student.class))).thenReturn(StudentBuilder.validStudent());

		BDDMockito.when(studentRepository.save(any(Student.class))).thenReturn(StudentBuilder.updateStudent());

		BDDMockito.when(studentRepository.findByFirstName(ArgumentMatchers.anyString()))
				.thenReturn(StudentBuilder.updateStudent());

		List<Student> students = Arrays.asList(StudentBuilder.createStudent(), StudentBuilder.createStudent());
		BDDMockito.when(studentRepository.findAll()).thenReturn(students);

		BDDMockito.when(studentRepository.findByEmail(ArgumentMatchers.anyString()))
				.thenReturn(StudentBuilder.createStudent());

		BDDMockito.doNothing().when(studentRepository).delete(any(Student.class));

	}

	@Test
	@Order(1)
	@DisplayName("Create student when successfully ")
	public void createStudent_whenSuccessful() {

		StudentDto studentDto = StudentDtoBuilder.createStudentDto();
		studentDto = studentService.save(studentDto);

		assertThat(studentDto).isNotNull();
		assertThat(studentDto.getEmail()).isEqualTo(StudentDtoBuilder.validStudentDto().getEmail());

	}

	@Test
	@Order(2)
	@DisplayName("Update student when successfully ")
	public void updateStudent_whenSuccessful() {

		StudentDto studentDto = StudentDtoBuilder.createStudentDto();
		StudentDto updateStudentDto = StudentDtoBuilder.updateStudentDto();

		updateStudentDto = studentService.update(studentDto.getFirstName(), updateStudentDto);

		assertThat(updateStudentDto).isNotNull();
		assertThat(updateStudentDto.getLastName()).isNotEqualTo(StudentDtoBuilder.validStudentDto().getLastName());

	}

	@Test
	@Order(3)
	@DisplayName("Find all student when successfully ")
	public void findAllStudent_whenSuccessful() {

		List<StudentDto> studentDtos = studentService.findAll();

		assertThat(studentDtos).isNotNull();
		assertThat(studentDtos.get(0).getEmail()).isEqualTo(StudentBuilder.validStudent().getEmail());

	}

	@Test
	@Order(4)
	@DisplayName("Find firstName student when successfully ")
	public void findByFistNameStudent_whenSuccessful() {

		String firstName = StudentDtoBuilder.createStudentDto().getFirstName();

		StudentDto studentDto = studentService.findByFirstName(firstName);

		assertThat(studentDto).isNotNull();
		assertThat(studentDto.getFirstName()).isEqualTo(StudentBuilder.validStudent().getFirstName());

	}

	@Test
	@Order(5)
	@DisplayName("Find email student when successfully ")
	public void findByEmailStudent_whenSuccessful() {

		String email = StudentDtoBuilder.createStudentDto().getEmail();
		StudentDto studentDto = studentService.findByEmail(email);

		assertThat(studentDto).isNotNull();
		assertThat(studentDto.getEmail()).isEqualTo(StudentBuilder.validStudent().getEmail());

	}

	@Test
	@Order(6)
	@DisplayName("Delete student when successfully ")
	public void deleteStudent_whenSuccessful() {

		String firstName = StudentDtoBuilder.createStudentDto().getFirstName();

		assertThatCode(() -> studentService.delete(firstName)).doesNotThrowAnyException();

	}

	@Test
	@Order(7)
	@DisplayName("Not found email student when successfully ")
	public void notFountEmailStudent_whenSuccessful() {
	
		  Throwable exception = assertThrows(ResourceNotFoundException.class, () -> studentService.findByEmail(null));
		  assertEquals("Found not email", exception.getMessage());
	}

	@Test
	@Order(8)
	@DisplayName("Not found firstName student when successfully ")
	public void notFountFirstNameStudent_whenSuccessful() {

		Throwable exception = assertThrows(ResourceNotFoundException.class, () -> studentService.findByFirstName(null));
		 assertEquals("Found not firstName", exception.getMessage());
	}
	
	@Test
	@Order(9)
	@DisplayName("Delete not found firstName student when successfully ")
	public void deleteNotFountFirstNameStudent_whenSuccessful() {
		
		Throwable exception = assertThrows(ResourceNotFoundException.class, () -> studentService.delete(null));
		assertEquals("Found not firstName", exception.getMessage());
	}
	
	@Test
	@Order(10)
	@DisplayName("Update not found email student when successfully ")
	public void UpdateNotFountFirstNameStudent_whenSuccessful() {
		
		StudentDto updateStudentDto = StudentDtoBuilder.updateStudentDto();
			
		Throwable exception =  assertThrows(ResourceNotFoundException.class, () -> studentService.update(null, updateStudentDto));
		assertEquals("Found not firstName", exception.getMessage());

	}

}
