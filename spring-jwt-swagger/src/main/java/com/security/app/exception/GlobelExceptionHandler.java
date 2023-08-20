package com.security.app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.security.app.paylods.ApiResponse;

@RestControllerAdvice
public class GlobelExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse api = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String > resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String filedName=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			resp.put(filedName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
		String message = ex.getMessage();
		ApiResponse api = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.BAD_REQUEST);
	}

}
