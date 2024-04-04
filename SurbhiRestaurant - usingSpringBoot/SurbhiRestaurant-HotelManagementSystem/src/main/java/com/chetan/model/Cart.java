package com.chetan.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart_details")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int cartId;
	String cartName;
	int quantity;
	float price;
	float totalPrice;
	boolean flag=false;
	
	@Column(name = "cartdate", columnDefinition = "DATE")
	LocalDate cartdate;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	Order order;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	User user;
	
	public Cart(String cartName, int quantity, float price, float totalPrice, LocalDate date,User user) {
		super();
		this.cartName = cartName;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.cartdate = date;
		this.user=user;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCartName() {
		return cartName;
	}

	public void setCartName(String cartName) {
		this.cartName = cartName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public LocalDate getCartdate() {
		return cartdate;
	}

	public void setCartdate(LocalDate cartdate) {
		this.cartdate = cartdate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart(int cartId, String cartName, int quantity, float price, float totalPrice, boolean flag,
			LocalDate cartdate, Order order, User user) {
		super();
		this.cartId = cartId;
		this.cartName = cartName;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.flag = flag;
		this.cartdate = cartdate;
		this.order = order;
		this.user = user;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	



}
