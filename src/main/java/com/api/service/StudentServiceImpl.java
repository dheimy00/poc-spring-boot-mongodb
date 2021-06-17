package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.api.dto.StudentDto;
import com.api.exceptions.general.ResourceNotFoundException;
import com.api.model.Student;
import com.api.repository.StudentRepository;
import com.api.utils.ObjectMapperUtils;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository,MongoTemplate mongoTemplate) {
		this.studentRepository = studentRepository;
	}

	@Override
	public StudentDto save(StudentDto studentDto) {

		Student student = ObjectMapperUtils.map(studentDto, Student.class);
		student = studentRepository.insert(student);
		return ObjectMapperUtils.map(student, StudentDto.class);
	}

	@Override
	public List<StudentDto> findAll() {
		List<Student> students = studentRepository.findAll();
		return ObjectMapperUtils.mapAll(students, StudentDto.class);
	}

	@Override
	public void delete(String firstName) {
		Student student = studentRepository.findByFirstName(firstName);
		if (student == null) {
			throw new ResourceNotFoundException("Found not firstName");
		}
		studentRepository.delete(student);
	}

	@Override
	public StudentDto update(String firstName, StudentDto studentDto) {
		Student student = studentRepository.findByFirstName(firstName);
		if (student == null) {
			throw new ResourceNotFoundException("Found not firstName");
		}
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
		student = studentRepository.save(student);
		return ObjectMapperUtils.map(student, StudentDto.class);
	}

	@Override
	public StudentDto findByFirstName(String firstName) {
		Student student = studentRepository.findByFirstName(firstName);
		if (student == null) {
			throw new ResourceNotFoundException("Found not firstName");
		}
		return ObjectMapperUtils.map(student, StudentDto.class);
	}

	@Override
	public StudentDto findByEmail(String email) {
		Student student = studentRepository.findByEmail(email);
		if (student == null) {
			throw new ResourceNotFoundException("Found not email");
		}
		return ObjectMapperUtils.map(student, StudentDto.class);
	}

}
