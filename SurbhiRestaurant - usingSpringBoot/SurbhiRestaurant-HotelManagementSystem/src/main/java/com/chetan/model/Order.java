package com.chetan.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int orderId;	
	
	@Column(name = "orderdate", columnDefinition = "DATE")
	LocalDate orderdate;
	double orderPrice;
	@OneToOne
	User user;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@JsonIgnore
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Cart> cartlist=new ArrayList();
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Cart> getCartlist() {
		return cartlist;
	}
	public void setCartlist(List<Cart> cartlist) {
		this.cartlist = cartlist;
	}
	public Order(int orderId, LocalDate orderdate, double orderPrice, User user, List<Cart> cartlist) {
		super();
		this.orderId = orderId;
		this.orderdate = orderdate;
		this.orderPrice = orderPrice;
		this.user = user;
		this.cartlist = cartlist;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
