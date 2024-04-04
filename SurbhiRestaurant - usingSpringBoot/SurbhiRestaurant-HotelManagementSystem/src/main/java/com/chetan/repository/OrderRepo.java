package com.chetan.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chetan.model.Order;
import com.chetan.model.User;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer>{

	public Order getOrderByUserAndOrderdate(User user, LocalDate now);

	public List<Order> getOrderByUser(User user);

	public List<Order> getOrdersByOrderdate(LocalDate now);
	
	@Query("select o from Order o where Month(orderdate) =?1 ")
	public List<Order> getOrdersByCurrnetMonth(int monthValue);

	public List<Order> getOrderByUserOrderByOrderdateDesc(User user);

}
