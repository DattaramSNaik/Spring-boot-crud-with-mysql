package com.example.VastEmpManagemnet.exception;

import org.springframework.http.HttpStatus;

public class HttpStatusException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private HttpStatus httpStatus;

	public HttpStatusException(String message, String errorCode, HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public HttpStatusException(String message, String errorCode, Throwable t, HttpStatus httpStatus) {
		super(message, t);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
