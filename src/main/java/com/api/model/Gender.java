package com.api.model;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	
	MALE("Male"),FEMALE("Female"),UNKNOWN_TO_SDK_VERSION(null);;
	
	private final String gender;
	
	Gender(String gender){
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}
	
	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(gender);
	}

	@JsonCreator
	public static Gender fromValue(String value) {
		if (value == null) {
			return null;
		}
		return Stream.of(Gender.values()).filter(e -> e.toString().equals(value)).findFirst()
				.orElse(UNKNOWN_TO_SDK_VERSION);
	}

	public static Set<Gender> knownValues() {
		return Stream.of(values()).filter(v -> v != UNKNOWN_TO_SDK_VERSION).collect(Collectors.toSet());
	}

}
