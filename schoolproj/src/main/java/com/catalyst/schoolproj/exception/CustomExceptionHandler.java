package com.catalyst.schoolproj.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException resEx){
		
		ErrorResponse errorResponse = new ErrorResponse(resEx.getMessage(), HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}

}
