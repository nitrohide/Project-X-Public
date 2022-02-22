package com.cognixia.jump.exception;

public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordException(String password) {
		super( " '" + password + "'" + " is invalid. Enter a password consisting of at least one uppercase, one lowercase, one number, and more than 8 characters.");
	}
}
