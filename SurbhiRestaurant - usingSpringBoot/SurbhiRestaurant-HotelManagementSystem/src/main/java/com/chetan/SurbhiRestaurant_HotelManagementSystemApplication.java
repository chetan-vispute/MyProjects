package com.chetan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.chetan.model.Item;
import com.chetan.model.Roles;
import com.chetan.model.User;
import com.chetan.repository.ItemRepo;
import com.chetan.repository.RolesRepo;
import com.chetan.service.UserService;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SurbhiRestaurant_HotelManagementSystemApplication implements CommandLineRunner{
	
	@Autowired
	ItemRepo itemRepository;
	@Autowired
	RolesRepo repository;
	
	@Autowired
	UserService service;
	
	public static void main(String[] args) {
		SpringApplication.run(SurbhiRestaurant_HotelManagementSystemApplication.class, args);
		System.out.println("WelCome to Surbhi Restaurant");
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Set<Roles>roles=new HashSet<Roles>();
		roles.add(new Roles("ADMIN"));
		roles.add(new Roles("USER"));
		if(repository.findAll().isEmpty()) {
		
			User u=new User("Chetan","Vispute","admin1","admin",7030395610l,"Chetan@mail");
			u.setRoles(roles);
			service.addUser(u);
			
			List<Item> items=new ArrayList<Item>();
			
			items.add(new Item("Pav Bhaji",67,45f,8,"Snack"));
			items.add(new Item("Bhel Puri",78,30f,9,"Snack"));
			items.add(new Item("Rice Plate",50,100f,7,"Veg"));
			
			
		}

		
		
	}

}
