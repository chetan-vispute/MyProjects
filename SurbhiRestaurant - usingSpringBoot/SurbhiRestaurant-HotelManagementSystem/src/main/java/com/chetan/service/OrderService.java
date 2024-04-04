package com.chetan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chetan.exception.UserNotFound;
import com.chetan.model.Cart;
import com.chetan.model.Order;
import com.chetan.model.User;
import com.chetan.repository.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	CartService cartService;
	
	@Autowired 
	UserService userService;
	
	public Order addOrder(int uid) {
		User user;
		try {
			Double sum=0d;
			user = userService.getUserDetails(uid);
			Order order=new Order();
			order= orderRepo.save(order);
			List<Cart> carts = cartService.getCartByUserAndDate(user,order);
			if(carts.isEmpty()) {
				return null;
			}
			for(Cart c:carts ) {
				//c.setOrder(order);
				sum=sum+c.getTotalPrice();
			}			
			
			order.setCartlist(carts);
			order.setOrderdate(LocalDate.now());
			order.setUser(user);
			order.setOrderPrice(sum);
			
			return orderRepo.save(order);
			
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Order getTodayOrderOfUser(int uid) {
		User user;
		try {
			user = userService.getUserDetails(uid);
			Order order =orderRepo.getOrderByUserAndOrderdate(user,LocalDate.now());
			return order;
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<Order> getAllOrdersOfUser(int uid) {
		try {
			User user=userService.getUserDetails(uid);
			return orderRepo.getOrderByUserOrderByOrderdateDesc(user);
			
		} catch (UserNotFound e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public List<Order> getTodayOrdersSale(){
		return orderRepo.getOrdersByOrderdate(LocalDate.now());
	}
	
	public double getSaleByCurrentMonth(){
		double sum=0d;
		List<Order> orders=orderRepo.getOrdersByCurrnetMonth(LocalDate.now().getMonthValue());
		for(Order o:orders) {
			sum=sum+o.getOrderPrice();
		}
		return sum;
	}

	public Order getLastOrderOfUser(int uid) {
		List<Order> list=getAllOrdersOfUser(uid);
		if(list.isEmpty()) {
			return null;
		}
		return list.get(list.size()-1);
	}

	public double getAmountOfTodaySale() {
		double sum=0;
		List<Order> orders=getTodayOrdersSale();
		for(Order o:orders) {
			sum=sum+o.getOrderPrice();
		}
		return sum;
	}
	
}
