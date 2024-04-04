package com.chetan.exception;

@SuppressWarnings("serial")
public class InvalidUserException extends RuntimeException{
	public InvalidUserException() {

		super("Exception :Invalid user");
	}

}
