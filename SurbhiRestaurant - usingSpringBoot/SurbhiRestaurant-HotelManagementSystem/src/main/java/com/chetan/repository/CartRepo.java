package com.chetan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chetan.model.Cart;
import com.chetan.model.Order;
import com.chetan.model.User;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer>{

	List<Cart> getCartsByUserAndCartdateAndFlag(User u, LocalDate now,boolean flag);

	List<Cart> getCartsByOrder(Order o);

	List<Cart> getCartsByUserAndCartdate(User user, LocalDate now);

	List<Cart> getCartsByUserAndFlagOrderByCartdateDesc(User user, boolean b);
	
	
	

}
