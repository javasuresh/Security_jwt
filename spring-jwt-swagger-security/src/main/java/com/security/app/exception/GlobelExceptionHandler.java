package com.security.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.security.app.paylods.ApiResponse;

@RestControllerAdvice
public class GlobelExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	String message=ex.getMessage();
	ApiResponse api=new ApiResponse(message,false);
	return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
	}

}
