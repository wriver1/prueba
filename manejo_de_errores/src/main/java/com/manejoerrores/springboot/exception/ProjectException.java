package com.manejoerrores.springboot.exception;

import org.springframework.http.HttpStatus;

public class ProjectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private HttpStatus status;
	
	public ProjectException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
	
	public ProjectException(HttpStatus status, String message, Throwable e) {
		super(message, e);
		this.status = status;
	}
	
	public ProjectException(HttpStatus status, Throwable e) {
		super(e);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	

}
