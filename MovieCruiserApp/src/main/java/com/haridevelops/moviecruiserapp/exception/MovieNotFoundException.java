package com.haridevelops.moviecruiserapp.exception;

@SuppressWarnings("serial")
public class MovieNotFoundException extends Exception {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MovieNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
	public String toString() {
		return "MovieNotFoundException [message=" + message + "]";
	}
}
