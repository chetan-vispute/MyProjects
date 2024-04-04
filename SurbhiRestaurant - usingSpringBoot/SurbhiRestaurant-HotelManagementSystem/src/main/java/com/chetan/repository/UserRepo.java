package com.chetan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chetan.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	public User getUserByEmailIdAndPassword(String userName, String password);

	public User getUserByUserName(String username);

}
