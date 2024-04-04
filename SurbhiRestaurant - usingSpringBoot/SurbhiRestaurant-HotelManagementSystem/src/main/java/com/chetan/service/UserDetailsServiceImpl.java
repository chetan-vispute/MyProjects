package com.chetan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.chetan.controller.UserController;
import com.chetan.model.User;
import com.chetan.repository.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepo repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=repository.getUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		UserController.id=user.getUserId();
		return new MyUserDetails(user);
		
	}

}
