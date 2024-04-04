package com.chetan.exception;

@SuppressWarnings("serial")
public class CartIdNotFound extends Exception {

	public CartIdNotFound(int id) {
		// TODO Auto-generated constructor stub
		super("Cart not found by id "+id);
	}
	
}
