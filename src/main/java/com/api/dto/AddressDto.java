package com.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	
	@NotBlank(message = "Country may not be null")
	private String country;
	@NotBlank(message = "City may not be null")
	private String city;
	@NotBlank(message = "State may not be null")
	private String state;
	@NotBlank(message = "Post code may not be null")
	private String postCode;
	
	
	public AddressDto() {
	}

}
