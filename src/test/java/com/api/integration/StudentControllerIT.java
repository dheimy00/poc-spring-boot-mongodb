package com.api.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.api.builders.StudentBuilder;
import com.api.builders.StudentDtoBuilder;
import com.api.dto.AddressDto;
import com.api.dto.StudentDto;
import com.api.model.Student;
import com.api.repository.StudentRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerIT {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private StudentRepository studentRepository;

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
		ResponseEntity<StudentDto> responseEntity = restTemplate.exchange("/api/v1/students", HttpMethod.POST,
				createJsonHttpEntity(studentDto), StudentDto.class);

		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(responseEntity.getBody().getId()).isNotNull();
		Assertions.assertThat(responseEntity.getBody().getEmail()).isEqualTo(studentDto.getEmail());
	}

	@Test
	@Order(2)
	@DisplayName("Update student when successfully ")
	public void updateStudent_whenSuccessful() {

		String firstName = StudentDtoBuilder.createStudentDto().getFirstName();
		StudentDto updateStudentDto = StudentDtoBuilder.updateStudentDto();

		ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/v1/students/{firstName}", HttpMethod.PUT,
				createJsonHttpEntity(updateStudentDto), Void.class, firstName);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody()).isNull();

	}

	@Test
	@Order(3)
	@DisplayName("Find all student when successfully ")
	public void findAllStudent_whenSuccessful() {

		String email = StudentDtoBuilder.createStudentDto().getEmail();
		List<StudentDto> responseEntity = restTemplate
				.exchange("/api/v1/students", HttpMethod.GET, null, new ParameterizedTypeReference<List<StudentDto>>() {
				}).getBody();

		Assertions.assertThat(responseEntity).isNotNull();
		Assertions.assertThat(responseEntity.get(0).getEmail()).isEqualTo(email);

	}

	@Test
	@Order(4)
	@DisplayName("Find firstName student when successfully ")
	public void findByFistNameStudent_whenSuccessful() {

		String firstName = StudentDtoBuilder.createStudentDto().getFirstName();

		ResponseEntity<StudentDto> responseEntity = restTemplate.getForEntity("/api/v1/students/firstName/{firstName}",
				StudentDto.class, firstName);

		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody().getFirstName()).isEqualTo(firstName);

	}

	@Test
	@Order(5)
	@DisplayName("Find emails student when successfully ")
	public void findByEmailStudent_whenSuccessful() {

		String email = StudentDtoBuilder.createStudentDto().getEmail();

		ResponseEntity<StudentDto> responseEntity = restTemplate.getForEntity("/api/v1/students/email/{email}",
				StudentDto.class, email);

		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody().getEmail()).isEqualTo(email);

	}

	@Test
	@Order(6)
	@DisplayName("Delete student when successfully ")
	public void deleteStudent_whenSuccessful() {

		String firstName = StudentDtoBuilder.createStudentDto().getFirstName();

		ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/v1/students/{firstName}", HttpMethod.DELETE,
				null, Void.class, firstName);

		assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@Order(7)
	@DisplayName("Create student return status code 400 bad requesty ")
	public void createStudentReturnStatusCode400BadRequest() {

		StudentDto studentDto = new StudentDto();

		AddressDto addressDto = new AddressDto();
		addressDto.setCity("Goiania");
		addressDto.setCountry("Brasil");
		addressDto.setState("Goiás");
		addressDto.setPostCode("74630-280");

		studentDto.setId("1");
		studentDto.setFirstName(null);
		studentDto.setLastName("Silva");
		studentDto.setEmail("teste@gmail.com");
		studentDto.setGender("Male");
		studentDto.setAddress(addressDto);
		studentDto.setFavouriteSubjects(List.of("Computer Science"));
		studentDto.setTotalSpentInBooks(BigDecimal.TEN);

		ResponseEntity<StudentDto> responseEntity = restTemplate.exchange("/api/v1/students", HttpMethod.POST,
				createJsonHttpEntity(studentDto), StudentDto.class);

		Assertions.assertThat(responseEntity).isNotNull();
		Assertions.assertThat(responseEntity.getBody().getFirstName()).isNull();
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}


	private HttpEntity<StudentDto> createJsonHttpEntity(StudentDto studentDto) {
		return new HttpEntity<>(studentDto, createJsonHeaders());
	}

	private static HttpHeaders createJsonHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;

	}
}
