package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbApplication.class, args);
	}
	
	/*
	 * @Bean CommandLineRunner runner( StudentRepository repository, MongoTemplate
	 * mongoTemplate) { return args -> { Address address = new
	 * Address("England","London", "NE9"); Student student = new Student("Joao",
	 * "Silva","joao@gmail.com", Gender.MALE, address, List.of("Computer Science"),
	 * BigDecimal.TEN, LocalDateTime.now());
	 * 
	 * String email = "joao@gmail.com"; // usingMongoTemplateAndQuery(repository,
	 * mongoTemplate, email, student);
	 * 
	 * repository.findByEmail(email).ifPresentOrElse(s -> { System.out.println(s +
	 * " already exists"); }, () -> { System.out.println("Inserting student " +
	 * student); repository.insert(student); }); };
	 * 
	 * }
	 * 
	 * private void usingMongoTemplateAndQuery(StudentRepository repository,
	 * MongoTemplate mongoTemplate, String email, Student student) { Query query =
	 * new Query(); query.addCriteria(Criteria.where("email").is(email));
	 * List<Student> students = mongoTemplate.find(query, Student.class);
	 * 
	 * if (students.size() > 1) { throw new
	 * IllegalStateException("Found many students with email " + email); }
	 * 
	 * if (students.isEmpty()) { System.out.println("Inserting student " + student);
	 * repository.insert(student); } else { System.out.println(student +
	 * " Already exists"); } }
	 */

}
