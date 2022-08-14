package com.store.storevmware.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.storevmware.Entity.Customer;
import com.store.storevmware.Entity.Item;
import com.store.storevmware.repository.CustomerRepository;
import com.store.storevmware.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}
	
	public String removeItem(Item item) {
		if(itemRepository.existsById(item.getId())) {
		itemRepository.delete(item);
		return "item removed succesfuly";
		}
		else return "item is not in the store";
	}
	
	public Item updateItem(Item item) {
		Optional<Item> checkExistItem = itemRepository.findById(item.getId());
		if(checkExistItem.isPresent()) {
			Item oldItem = checkExistItem.get();
			oldItem.setAmount(item.getAmount());
			oldItem.setDiscription(item.getDiscription());
			oldItem.setPrice(item.getPrice());
			itemRepository.save(oldItem);
			return oldItem;
		}else {
			return new Item();
		}
	}

	public Item findById(int itemId) {
		return itemRepository.findById(itemId).get();
		
	}
	
	public List<Item> findAllByCatalogId(int catalogId){
		return itemRepository.getItemsByCatalogId(catalogId);
	}
	
	public List<Item> findAllItems(){
		return itemRepository.findAll();
	}
	
	public Item purchaseItem(int itemId, HttpServletRequest request) {
		Optional<Item> item = itemRepository.findById(itemId);
		//customer money check and supply check
		Customer customer = (Customer) request.getSession().getAttribute("USER");
		if (item.isPresent() && item.get().getAmount() > 0) {
			if(customer.getAmountOfMoney() >= item.get().getPrice()) {
				item.get().setAmount(item.get().getAmount() -1);
				itemRepository.save(item.get());
				customer.setAmountOfMoney(customer.getAmountOfMoney() - item.get().getPrice());
				customerRepository.save(customer);
			}
		}
		return item.get();
	}
	
}
