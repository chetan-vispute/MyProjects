package com.chetan.exception;

@SuppressWarnings("serial")
public class MenuNotExist extends RuntimeException{
	public MenuNotExist() {
	super("No item in menu");
	}
}
