package com.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
	
	Student findByEmail(String email);
	
	Student findByFirstName(String firstName);

}
