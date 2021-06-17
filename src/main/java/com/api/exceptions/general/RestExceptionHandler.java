package com.api.exceptions.general;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			HttpServletRequest request) {

		ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
		resourceNotFoundDetails.setTitle("Resource Not Found");
		resourceNotFoundDetails.setStatus(HttpStatus.NOT_FOUND.value());
		resourceNotFoundDetails.setDetail(exception.getMessage());
		resourceNotFoundDetails.setTimeStamp(LocalDateTime.now());
		resourceNotFoundDetails.setDeveloperMessage(exception.getClass().getName());
		
		return new ResponseEntity<>(resourceNotFoundDetails, null, HttpStatus.NOT_FOUND);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionDetails exceptionDetails = new ExceptionDetails();
						
		exceptionDetails.setTitle(exception.getCause().getMessage());
		exceptionDetails.setStatus(HttpStatus.NOT_FOUND.value());
		exceptionDetails.setDetail(exception.getMessage());
		exceptionDetails.setTimeStamp(LocalDateTime.now());
		exceptionDetails.setDeveloperMessage(exception.getClass().getName());
		
		return new ResponseEntity<>(exceptionDetails, headers, status);
	}
		 
}
