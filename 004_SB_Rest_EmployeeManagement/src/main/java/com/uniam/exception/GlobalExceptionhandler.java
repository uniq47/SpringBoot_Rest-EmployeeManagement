package com.uniam.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionhandler {

	@ExceptionHandler(ResourceNotFoundexception.class)
	public ResponseEntity<?> globalFileNotFound(Exception ex, WebRequest req)
	{
		ErrorDetails details = new ErrorDetails(new Date(), ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionhandler(Exception ex, WebRequest req)
	{
		ErrorDetails details = new ErrorDetails(new Date(), ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
