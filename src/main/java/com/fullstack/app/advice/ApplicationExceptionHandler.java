package com.fullstack.app.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler({ TransactionSystemException.class })
//	public String handleConstraintViolation(Exception ex, WebRequest request) {
//	    Throwable cause = ((TransactionSystemException) ex).getRootCause();
//	    if (cause instanceof ConstraintViolationException) {
//	        Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
//	        // do something here
//	       
//	    }
//	    return "should not be null";
//	}
	
	
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 @ExceptionHandler(NoSuchElementException.class)
	 public Map<String, String> userException(NoSuchElementException ex)
	 {
		 Map<String, String> map=new HashMap<String, String>();
		 
		 map.put("errorMessage","No record found");
		 return map;
	 }
	 
}

