package com.chetan.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chetan.exception.CartIdNotFound;
import com.chetan.exception.ItemNotFoundById;
import com.chetan.exception.UserNotFound;
import com.chetan.model.Cart;
import com.chetan.model.Item;
import com.chetan.model.Order;
import com.chetan.model.User;
import com.chetan.repository.CartRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {

	@Autowired
	CartRepo cartRepo;
	@Autowired
	ItemService  itemService;
	@Autowired
	OrderService orderService;

	@Autowired 
	UserService userService;

	public Cart addInCart(int uid,int itemId,int quant) {
		try {	
			Item item=itemService.getItemById(itemId);
			User user=userService.getUserDetails(uid);

			if(itemService.updateItemStock(item, quant)) {

				Cart c=new Cart();
				c.setCartdate(LocalDate.now());
				c.setCartName(item.getItemName());
				c.setPrice(item.getPrice());
				c.setQuantity(quant);
				c.setTotalPrice(item.getPrice()*quant);
				c.setUser(user);
				cartRepo.save(c);
				return c;
			}
			else {
				System.out.println("less qunatity of item");
				return null;
			}

		} catch (ItemNotFoundById e) {
			System.out.println(e.getMessage());	
		}
		catch (UserNotFound e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Cart> getCartByUserAndDate(User u,Order order){
		List<Cart> carts=cartRepo.getCartsByUserAndCartdateAndFlag(u,LocalDate.now(),false);
		for(Cart c:carts) {
			c.setFlag(true);
			c.setOrder(order);
			cartRepo.save(c);
		}
		return carts;
	}
	public List<Cart> getCartByUserAndDateId(int uid){
		User user;
		try {
			user = userService.getUserDetails(uid);
			return cartRepo.getCartsByUserAndCartdateAndFlag(user,LocalDate.now(),true);
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Cart> getCartByUserAndDateIdAll(int uid){
		User user;
		try {
			user = userService.getUserDetails(uid);
			return cartRepo.getCartsByUserAndCartdate(user,LocalDate.now());
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Cart> getUnOrderCarts(int id){
		User user;
		try {
			user = userService.getUserDetails(id);
			return cartRepo.getCartsByUserAndCartdateAndFlag(user,LocalDate.now(),false);
		} catch (UserNotFound e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public void deleteCartById(int id) throws CartIdNotFound {
		if(cartRepo.existsById(id)) {
			Cart c=cartRepo.findById(id).get();
			if(c.isFlag()) {
				System.out.println("Can't be delete this order alreday proceed ");
				return;
			}
			itemService.updateItemQuantity(c.getCartName(), c.getQuantity());
			cartRepo.delete(c);
			System.out.println("Cart is deleted");

		}
		else {
			throw new CartIdNotFound(id);
		}
	}
	
	public List<Cart> getCartByOrderId(Order o) {
		return cartRepo.getCartsByOrder(o);
	}

	public List<Cart> getCartByUserId(int id) {
		User user;
		try {
			user = userService.getUserDetails(id);
			return cartRepo.getCartsByUserAndFlagOrderByCartdateDesc(user,true);
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
