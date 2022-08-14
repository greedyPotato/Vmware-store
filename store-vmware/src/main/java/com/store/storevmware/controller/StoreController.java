package com.store.storevmware.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/store")
public class StoreController {
	//THESE APIS FOR AN CUSTOMER LOGGED TO THE SYSTEM
	@Autowired
	CatalogService catalogService;
	@Autowired
	ItemService itemService;
	
	//CUSTOMER - EXPOSE ALL CATALOGS IN DATABASE
	@GetMapping("/catalogs")
	public List<Catalog> getCatalogs(HttpServletRequest request){
		if(request.getSession().getAttribute("USER") instanceof Customer)
		return catalogService.getCatalogs();
		else
			return null;
	}
	
	//CUSTOMER - EXPOSE ALL ITEMS IN DATABASE
	@GetMapping("/items")
	public List<Item> getItems(HttpServletRequest request){
		if(request.getSession().getAttribute("USER") instanceof Customer)
		return itemService.findAllItems();
		else
			return null;
	}
	
	//CUSTOMER - EXPOSE ALL ITEMS IN A CATALOG DATABASE
	@GetMapping("/catalogs/{catalogId}/items")
	public List<Item> getItemsFromCatalog(@PathVariable int catalogId, HttpServletRequest request){
		if(request.getSession().getAttribute("USER") instanceof Customer)
		return itemService.findAllByCatalogId(catalogId);
		else 
			return null;
	}
	
	////CUSTOMER - PURCHSE AN ITEM BY ID AND SPEND MONEY ON IT
	@GetMapping("items/{itemId}/purchase")
	public Item purchaseSingleItem(@PathVariable int itemId, HttpServletRequest request) {
		if(request.getSession().getAttribute("USER") instanceof Customer)
		return itemService.purchaseItem(itemId, request);
		else
			return null;
	}

}
