package com.api.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {
	
	private String id;
	
	@NotBlank(message = "FirstName may not be null")
	private String firstName;
	
	@NotBlank(message = "LastName may not be null")
	private String lastName;
	
	@NotBlank(message = "Email may not be null")
	@Email
	private String email;
	
	@NotBlank(message = "Gender may not be null")
	private String gender;
	
	@Valid
	private AddressDto address;
	
	@NotNull(message = "Favourite subjects may not be null")
	private List<String> favouriteSubjects;
	
	@NotNull(message = "TotalSpentInBooks may not be null")
	private BigDecimal totalSpentInBooks;	
	

}
