package com.chetan.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chetan.exception.CartIdNotFound;
import com.chetan.model.Cart;
import com.chetan.model.Item;
import com.chetan.model.Order;
import com.chetan.model.Roles;
import com.chetan.model.User;
import com.chetan.repository.RolesRepo;
import com.chetan.service.CartService;
import com.chetan.service.ItemService;
import com.chetan.service.OrderService;
import com.chetan.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	public static int id;
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;
	@Autowired
	CartService  cartService;
	@Autowired
	RolesRepo repository;
	
	@Autowired
	ItemService itemService;
	
	@PostMapping("/addCart")
	public Cart addItemInCart(@RequestParam int itemId,@RequestParam int quant) {
		return cartService.addInCart(id, itemId, quant);
		
	}
	@GetMapping("/getAllcarts")
	public List<Cart> getAllCarts(){
		return cartService.getCartByUserAndDateIdAll(id);
	}
	
	@GetMapping("/getAllOrderCart")
	public List<Cart> getAllOrderCarts(){
		return cartService.getCartByUserId(id);
	}
	@GetMapping("/getUnOrderCart")
	public List<Cart> getUnOrderCarts(){
		return cartService.getUnOrderCarts(id);
	}
	
	@DeleteMapping("/deleteCart")
	public void deleteCartById( @RequestParam int cartId) {
		 try {
			cartService.deleteCartById(cartId);
		} catch (CartIdNotFound e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/addToOrder")
	public Order addOrder() {
	
		return orderService.addOrder(id);
	}
	
	@GetMapping("/getAllOrders")
	public List<Order> getAllOrdersHistory(){
	
		return orderService.getAllOrdersOfUser(id);
	}
	
	@GetMapping("/getItems")
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}
	@GetMapping("/getItemByCategory")
	public List<Item> getAllItems(@RequestParam String category) {
		return itemService.getItemByCategoryLike(category);
	}
	
	@GetMapping("/lastOrder")
	public Order getLastOrder() {

		return orderService.getLastOrderOfUser(id);
	}
	
	@GetMapping("/lastCartOrder")
	public List<Cart> getLastCartOrder(){

		Order o= orderService.getLastOrderOfUser(id);
		return cartService.getCartByOrderId(o);
	}
	
	@PostMapping("/register")
	public User addUser(@RequestBody User u) {
		Roles r=repository.getRolesByName("USER");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Set<Roles> sets=new HashSet();
		sets.add(r);
		u.setRoles(sets);
		return userService.addUser(u);
	}
	
	
	
	
	
	
}
