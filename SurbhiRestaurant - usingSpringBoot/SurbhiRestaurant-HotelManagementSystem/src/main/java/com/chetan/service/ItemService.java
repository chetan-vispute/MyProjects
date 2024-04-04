package com.chetan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chetan.exception.ItemNotFoundById;
import com.chetan.model.Item;
import com.chetan.repository.ItemRepo;

@Service
public class ItemService {
	
	@Autowired
	ItemRepo itemRepo;
	
	public Item addItem(Item item) {
		return itemRepo.save(item);
	}
	
	public Item updateItem(Item item) {
		if(itemRepo.existsById(item.getItemId())) {
			Item oldItem= itemRepo.findById(item.getItemId()).get();
			if(item.getItemName()!=null) {
				oldItem.setItemName(item.getItemName());
			}
			if(item.getPrice()!=0) {
				oldItem.setPrice(item.getPrice());
			}
			if(item.getRating()!=0) {
				oldItem.setRating(item.getRating());
			}
			if(item.getStock()!=0) {
				oldItem.setStock(item.getStock());
			}
			return itemRepo.save(oldItem);
		}
		return null;
	}
	
	public Item getItemById(int id) throws ItemNotFoundById {
		if(itemRepo.existsById(id)) {
			return itemRepo.findById(id).get();
		}
		else {
			throw new ItemNotFoundById(id);
		}
	}
	
	public List<Item> getAllItems(){
		return itemRepo.findAll();
	}
	
	public void deleteItem(int id) throws ItemNotFoundById {
		if(itemRepo.existsById(id)) {
			itemRepo.deleteById(id);
			return;
		}
		throw new ItemNotFoundById(id);
	}
	
	public List<Item> getAvailbaleItem(){
		return itemRepo.getItemsByStockGreaterThan(0);
	}
	
	public List<Item> getItemByName(String name){
		return itemRepo.getItemByItemName(name);
	}
	
	public List<Item> getItemByCategory(String name){
		return itemRepo.getItemByCategory(name);
	}
	
	public boolean updateItemStock(Item item,int i) {
		
		int remain=item.getStock()-i;
		if(remain>0) {
		item.setStock(remain);
		itemRepo.save(item);
		return true;
		}
		return false;
	}
	
	public void updateItemQuantity(String string,int qunat) {
		List<Item> item=itemRepo.getItemByItemName(string);
		int q=item.get(0).getStock()+qunat;
		item.get(0).setStock(q);
		itemRepo.save(item.get(0));
	}
	public List<Item> getItemByCategoryLike(String category){
		return itemRepo.getItemByCategoryLike(category);
	}

	
}
