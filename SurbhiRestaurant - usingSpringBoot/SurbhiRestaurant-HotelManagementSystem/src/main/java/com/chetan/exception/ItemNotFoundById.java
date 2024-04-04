package com.chetan.exception;

@SuppressWarnings("serial")
public class ItemNotFoundById extends Exception {
	
	public ItemNotFoundById(int id) {
		// TODO Auto-generated constructor stub
		super("Item Not Found with id "+id);
	}
}
