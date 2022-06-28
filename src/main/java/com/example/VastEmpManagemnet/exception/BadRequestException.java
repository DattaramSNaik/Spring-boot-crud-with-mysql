package com.example.VastEmpManagemnet.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpStatusException{

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message, String errorCode) {
		super(message, errorCode, HttpStatus.BAD_REQUEST);
	}

	public BadRequestException(String message, String errorCode, Throwable t) {
		super(message, errorCode, t, HttpStatus.BAD_REQUEST);
	}

}
