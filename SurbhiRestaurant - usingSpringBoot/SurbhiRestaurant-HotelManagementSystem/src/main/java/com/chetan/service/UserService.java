package com.chetan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chetan.exception.UserNotFound;
import com.chetan.model.User;
import com.chetan.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	public User addUser(User u) {
		try {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return userRepo.save(u);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void deleteUser(int id) throws UserNotFound {
		if(userRepo.existsById(id)) {
			User u=userRepo.findById(id).get();
			u.setEnabled(0);
			userRepo.save(u);
			System.out.println("User with id "+id+" is disbled .");
		}
		else {
			throw new UserNotFound();
		}
	}
	public User getUserByUsernameAndPassowrd(String userName,String password) {
		return userRepo.getUserByEmailIdAndPassword(userName,password);
	}
	public User updateUser(User u) {
		if(userRepo.existsById(u.getUserId())) {
			User old=userRepo.findById(u.getUserId()).get();
			if(u.getContactNo()!=0) {
				old.setContactNo(u.getContactNo());
			}
			if(u.getEmailId()!=null) {
				old.setEmailId(u.getEmailId());
			}
			if(u.getFirstName()!=null) {
				old.setFirstName(u.getFirstName());
			}
			if(u.getLastName()!=null) {
				old.setLastName(u.getLastName());
			}
			if(u.getPassword()!=null) {
				old.setPassword(u.getPassword());
			}
			if(u.getPassword()!=null) {
				old.setPassword(passwordEncoder.encode(u.getPassword()));
			}
			
			return userRepo.save(old);			
		}
		return null;
	}
	public User getUserDetails(int id) throws UserNotFound {
		if(userRepo.existsById(id)) {
			return userRepo.findById(id).get();
		}
		else {
			throw new UserNotFound();
		}
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
}
