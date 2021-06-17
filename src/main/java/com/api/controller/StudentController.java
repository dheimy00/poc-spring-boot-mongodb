package com.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.dto.StudentDto;
import com.api.service.StudentService;
import com.api.utils.GeneralMessage;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<StudentDto> save(@Valid @RequestBody StudentDto studentDto) {

		StudentDto saveStudentDto = studentService.save(studentDto);

		HttpHeaders responseHeaders = new HttpHeaders();
		URI newURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveStudentDto.getId()).toUri();
		responseHeaders.setLocation(newURI);

		return new ResponseEntity<>(saveStudentDto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDto>> findAll() {

		List<StudentDto> students = studentService.findAll();

		if (students.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/{firstName}", method = RequestMethod.DELETE)
	public ResponseEntity<GeneralMessage> delete(@PathVariable String firstName) {
		studentService.delete(firstName);
		return new ResponseEntity<>(new GeneralMessage("Student deleted successfully "), HttpStatus.OK);
	}

	@RequestMapping(value = "/{firstName}", method = RequestMethod.PUT)
	public ResponseEntity<GeneralMessage> update(@PathVariable String firstName, @RequestBody StudentDto studentDto) {
		studentService.update(firstName, studentDto);
		return new ResponseEntity<>(new GeneralMessage("Student updated successfully "), HttpStatus.OK);
	}

	@RequestMapping(value = "/firstName/{firstName}", method = RequestMethod.GET)
	public ResponseEntity<StudentDto> searchFirstName(@PathVariable String firstName) {
		return new ResponseEntity<>(studentService.findByFirstName(firstName), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<StudentDto> searchEmail(@PathVariable String email) {
		return new ResponseEntity<>(studentService.findByEmail(email), HttpStatus.OK);
	}

}
