package com.api.service;

import java.util.List;

import com.api.dto.StudentDto;

public interface StudentService {

	StudentDto save(StudentDto studentDto);
	List<StudentDto> findAll();
	void delete(String firstName);
	StudentDto update(String firstName,StudentDto studentDto);
	StudentDto findByFirstName(String firstName);
	StudentDto findByEmail(String firstName);
}
