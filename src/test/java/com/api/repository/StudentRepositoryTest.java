package com.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.api.builders.StudentBuilder;
import com.api.model.Student;

@ExtendWith(SpringExtension.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@DisplayName("Student Repository Test")
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
		
	@BeforeEach
	void cleanUp() {
		this.studentRepository.deleteAll();

	}

	@Test
	@DisplayName("Create student when successfully ")
	public void createStudent_whenSuccessful() {
		Student student = StudentBuilder.createStudent();

		Student saveStudent = studentRepository.save(student);

		Assertions.assertThat(saveStudent.getId()).isNotNull();
		Assertions.assertThat(saveStudent.getFirstName()).isNotNull();
		Assertions.assertThat(saveStudent.getFirstName()).isEqualTo(student.getFirstName());
	}

	@Test
	@DisplayName("Update student when successfully")
	public void updateStudent_whenSuccessful() {
		Student student = StudentBuilder.createStudent();

		Student saveStudent = studentRepository.save(student);
		saveStudent.setFirstName("Dheimy");
		Student updateStudent = studentRepository.save(saveStudent);
		Assertions.assertThat(saveStudent.getId()).isNotNull();
		Assertions.assertThat(saveStudent.getFirstName()).isNotNull();
		Assertions.assertThat(saveStudent.getFirstName()).isEqualTo(updateStudent.getFirstName());

	}

	@Test
	@DisplayName("Delete student when successfully")
	public void deleteByStudent_whenSuccessful() {

		Student student = StudentBuilder.createStudent();
		Student saveStudent = studentRepository.save(student);
		studentRepository.delete(saveStudent);
		Optional<Student> studentOptional = studentRepository.findById(saveStudent.getId());

		Assertions.assertThat(studentOptional.isEmpty()).isTrue();

	}

	@Test
	@DisplayName("Find by name student when successfully")
	public void findByStudent_whenSuccessful() {

		Student student = StudentBuilder.createStudent();
		Student saveStudent = studentRepository.save(student);

		Student findStudent = studentRepository.findByFirstName(saveStudent.getFirstName());
		assertThat(findStudent.getFirstName()).isEqualTo(student.getFirstName());
		assertThat(findStudent.getAddress().getCity()).isEqualTo(student.getAddress().getCity());

	}

	@Test
	@DisplayName("If not found firstName student when successfully")
	public void NotFoundFindByStudent_whenSuccessful()  {

		studentRepository.save(StudentBuilder.createStudent());
		
		String firstName = "Raquel";
		Student studentFirstName = studentRepository.findByFirstName(firstName);
		 assertThat(studentFirstName).isNull();
	}
	
	
	@Test
	@DisplayName("Find all student when successfully")
	public void findAllStudent_whenSuccessful()  {

		Student saveStudent = studentRepository.save(StudentBuilder.createStudent());
		List<Student> students = studentRepository.findAll();
		
		assertThat(students).isNotEmpty();
		assertThat(students.get(0).getFirstName()).isEqualTo(saveStudent.getFirstName());
		
	}
	
	

}
