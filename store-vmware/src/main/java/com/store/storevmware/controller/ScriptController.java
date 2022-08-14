package com.store.storevmware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.storevmware.Entity.Catalog;
import com.store.storevmware.Entity.Customer;
import com.store.storevmware.Entity.Item;
import com.store.storevmware.Entity.Owner;
import com.store.storevmware.service.CatalogService;
import com.store.storevmware.service.CustomerService;
import com.store.storevmware.service.ItemService;
import com.store.storevmware.service.OwnerService;

@RestController
public class ScriptController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	OwnerService ownerService;
	@Autowired
	CatalogService catalogService;
	@Autowired
	ItemService itemService;
	
	@GetMapping("/runScript")
	public void RunScript() {
		Customer customer1 = new Customer(1,"idan davidi", 100000,"idandvd@gmail.com","1234");
		Customer customer2 = new Customer(2,"moshe cohen", 150,"moshe@gmail.com","5555");
		Owner owner1 = new Owner(1, "liron@gmail.com", "liliron", "liron zvi");
		Owner owner2 = new Owner(2, "dudu@gmail.com", "dudu123", "dudu levi");
		
		//create 2 customers and 2 owners in database:
		customerService.createCustomer(customer1);
		customerService.createCustomer(customer2);
		ownerService.createOwner(owner1);
		ownerService.createOwner(owner2);
		
		//crate catalog of computers and items
		Catalog computers = new Catalog(1,"computers");
		Item item1 = new Item(1,100,5,"keyboard");
		Item item2 = new Item(2,50,4,"mouse");
		Item item3 = new Item(3,5000,8,"pc");
		Item item4 = new Item(4,250,7,"screen");
		Item item5 = new Item(5,80,11,"mouse-pad");
		
		catalogService.addCatalog(computers);
		item1.setCatalog(computers);
		item2.setCatalog(computers);
		item3.setCatalog(computers);
		item4.setCatalog(computers);
		item5.setCatalog(computers);
		itemService.saveItem(item1);
		itemService.saveItem(item2);
		itemService.saveItem(item3);
		itemService.saveItem(item4);
		itemService.saveItem(item5);
		
		
		//crate kitchen tools catalog and items
		Catalog kitchenTools = new Catalog(2,"kitchen tools");
		Item item6 = new Item(6,55,22,"blender");
		Item item7 = new Item(7,150,4,"microwave");
		
		catalogService.addCatalog(kitchenTools);
		item6.setCatalog(kitchenTools);
		item7.setCatalog(kitchenTools);
		itemService.saveItem(item6);
		itemService.saveItem(item7);
		
	
		
		
		
		
	}

}
