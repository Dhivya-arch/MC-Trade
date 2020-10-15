package com.customer.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * customer exception class
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR )
public class CustomException extends Exception{


public CustomException(String message) {
		
		super(message);
		
	}

	
	
}
