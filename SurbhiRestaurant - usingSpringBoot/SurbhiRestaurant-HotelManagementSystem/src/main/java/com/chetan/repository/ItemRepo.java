package com.chetan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chetan.model.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer>{
	public List<Item> getItemsByStockGreaterThan(int i);
	public List<Item> getItemByItemName(String name);
	public List<Item> getItemByCategory(String name);
	
	@Query("Select i from Item i where i.category like ?1%")
	public List<Item> getItemByCategoryLike(String category);
}
