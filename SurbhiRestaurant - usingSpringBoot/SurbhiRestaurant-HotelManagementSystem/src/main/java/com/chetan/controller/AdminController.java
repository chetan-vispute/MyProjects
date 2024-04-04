package com.chetan.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chetan.exception.ItemNotFoundById;
import com.chetan.exception.UserNotFound;
import com.chetan.model.Item;
import com.chetan.model.Order;
import com.chetan.model.Roles;
import com.chetan.model.User;
import com.chetan.repository.RolesRepo;
import com.chetan.service.ItemService;
import com.chetan.service.OrderService;
import com.chetan.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	RolesRepo repository;
	
	
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User u) {
		Roles r=repository.getRolesByName("USER");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set<Roles> sets=new HashSet();
		if(r!=null) {
			sets.add(r);
			u.setRoles(sets);
			return userService.addUser(u);
		}
		return null;
	}
	
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User u) {
		return userService.updateUser(u);
	}
	
	@DeleteMapping("/deleteUser")
	public void deleteUserById(@RequestParam int uid) {
		 try {
			userService.deleteUser(uid);
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/getUserById")
	public User getUserById(@RequestParam int id) {
		try {
			return userService.getUserDetails(id);
		} catch (UserNotFound e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/addItem")
	public Item addItem(@RequestBody Item item) {
		return itemService.addItem(item);
	}
	
	@DeleteMapping("/deleteItem")
	public void deleteItem(@RequestParam int id) {
		try {
			itemService.deleteItem(id);
		} catch (ItemNotFoundById e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	@PutMapping("/updateItem")
	public Item updateItem(@RequestBody Item item) {
		return itemService.updateItem(item);
	}
	
	@GetMapping("/getItem")
	public Item getItemById(@RequestParam int id) {
		try {
			return itemService.getItemById(id);
		} catch (ItemNotFoundById e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	@GetMapping("/getAllItems")
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}
	@GetMapping("/getAvailableItems")
	public List<Item> getAvailableItem()
	{
		return itemService.getAvailbaleItem();
	}
	
	@GetMapping("/getTodayOrders")
	public List<Order> getTodaySale() {
		return orderService.getTodayOrdersSale();
	}
	
	@GetMapping("/getToOrderSale")
	public double getAmountOfTodaySale() {
		return orderService.getAmountOfTodaySale();
	}
	
	@GetMapping("/getMoOrderSale")
	public double getCurreentMonthSale() {
		return orderService.getSaleByCurrentMonth();
	}
	
	@GetMapping("/getOrderByUser")
	public List<Order> getOrderDetailsOfUsers(@RequestParam int userId) {
		return orderService.getAllOrdersOfUser(userId);
	}
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		Roles r=repository.getRolesByName("ADMIN");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Set<Roles> sets=new HashSet();
		sets.add(r);
		user.setRoles(sets);
		return userService.addUser(user);
	}
	
}
