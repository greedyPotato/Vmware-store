package com.store.storevmware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.store.storevmware.Entity.Catalog;
import com.store.storevmware.Entity.Item;
import com.store.storevmware.Entity.Owner;
import com.store.storevmware.service.CatalogService;
import com.store.storevmware.service.ItemService;


@RestController
@RequestMapping("/management")
public class OwnerController {
	//THESE APIS WORKS ONLY FOR AN OWNER LOGGED IN TO THE SYSTEM
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private CatalogService catalogService;
	
	//OWNER - ADD AN ITEM
	@PostMapping("/addItem")
	public ResponseEntity<Item> addItem(@RequestBody @Valid Item item, HttpServletRequest request) {
		if(request.getSession().getAttribute("OWNER") instanceof Owner)
			return new ResponseEntity<Item>(itemService.saveItem(item), HttpStatus.CREATED);	
		else
		return  new ResponseEntity<Item>(new Item(), HttpStatus.EXPECTATION_FAILED);
	}

	//OWNER - DELETES AN ITEM
	@DeleteMapping("/removeItem")
	public String removeItem(@RequestBody @Valid Item item, HttpServletRequest request) {
		if(request.getSession().getAttribute("OWNER") instanceof Owner)
			return itemService.removeItem(item);
		else
			return "only the owner can change the info";
	}
	
	//OWNER - UPDATES AN ITEM
	@PutMapping("/updateItem")
	public ResponseEntity<Item> UpdateItem(@RequestBody @Valid Item item, HttpServletRequest request) {
		if(request.getSession().getAttribute("OWNER") instanceof Owner)
		return new ResponseEntity<> (itemService.updateItem(item), HttpStatus.CREATED);
		else
			return new ResponseEntity<> (new Item(), HttpStatus.EXPECTATION_FAILED);
	}
	
	
	//OWNER - ADDS A CATALOG
	@PostMapping("/addCatalog")
	public ResponseEntity<Catalog> addCatalog(@RequestBody @Valid Catalog catalog, HttpServletRequest request) {
		if(request.getSession().getAttribute("OWNER") instanceof Owner)
		return new ResponseEntity<>(catalogService.addCatalog(catalog),HttpStatus.CREATED);
		else
			return new ResponseEntity<>(new Catalog(),HttpStatus.EXPECTATION_FAILED);
	}	
	//OWNER - REMOVES A CATALOG
	@DeleteMapping("/removeCatalog")
	public void removeCatalog(@RequestBody @Valid Catalog catalog, HttpServletRequest request) {
		if(request.getSession().getAttribute("OWNER") instanceof Owner)
		catalogService.removeCatalog(catalog);
	}
	
	//OWNER - UPDATE CATALOG
	@PutMapping("/updateCatalog")
	public ResponseEntity<Catalog> UpdateCatalog(@RequestBody @Valid Catalog catalog, HttpServletRequest request) {
		if(request.getSession().getAttribute("OWNER") instanceof Owner)
		return new ResponseEntity<>(catalogService.updateCatalog(catalog), HttpStatus.CREATED);
		else
			return new ResponseEntity<>(new Catalog(), HttpStatus.EXPECTATION_FAILED);
	}
	
	//OWNER - ASSIGN AN ITEM TO A CATALOG
	@PutMapping("/catalog/{catalogId}/item/{itemId}")
	public ResponseEntity<Item> assignItemToCatalog(@PathVariable int catalogId, @PathVariable int itemId, HttpServletRequest request) {
		if(request.getSession().getAttribute("OWNER") instanceof Owner) {
		Item item = itemService.findById(itemId);
		Catalog catalog = catalogService.findById(catalogId);
		item.setCatalog(catalog);
		return new ResponseEntity<>(itemService.saveItem(item), HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(new Item(), HttpStatus.EXPECTATION_FAILED);
		}
	}
}
