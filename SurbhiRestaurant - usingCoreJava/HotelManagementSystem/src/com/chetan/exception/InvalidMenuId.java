package com.chetan.exception;

@SuppressWarnings("serial")
public class InvalidMenuId extends RuntimeException{
	public InvalidMenuId() {

		super("Menu Id Not Found");
	}
	
}
