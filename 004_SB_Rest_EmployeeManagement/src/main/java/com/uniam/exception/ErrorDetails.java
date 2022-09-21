package com.uniam.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Customize Error response Structure
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;
	
}
