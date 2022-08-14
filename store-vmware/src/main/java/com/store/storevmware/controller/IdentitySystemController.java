package com.store.storevmware.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.store.storevmware.Entity.Customer;
import com.store.storevmware.Entity.LoginCredentials;
import com.store.storevmware.Entity.Owner;
import com.store.storevmware.service.CustomerService;
import com.store.storevmware.service.OwnerService;

@RequestMapping("/identifySystem")
@RestController
public class IdentitySystemController {
	//THIS IS IDENTIFY SYSTEM FOR A CUSTOMER OR A OWNER OF THE SHOP
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OwnerService ownerService;
	
	//OWNER CAN SIGN UP TO THE DATABASE
	@PostMapping("/ownerSignUp")
	public ResponseEntity<Owner> ownerSignUp(@RequestBody @Valid Owner owner) {
		
		return ownerService.createOwner(owner);
	}
	//CUSTOMER CAN SIGN UP TO THE DATABASE
	@PostMapping("/customerSignUp")
	public ResponseEntity<Customer> customerSignUp(@RequestBody @Valid Customer customer) {
		return customerService.createCustomer(customer);
		
	}
	//CUSTOMER CAN LOGIN TO THE SYSTEM AND MAKE API CALLS ONLY SUIT FOR HIM(SESSION USE CASE)
	@GetMapping("/customer/login")
	public String customerLogin(HttpServletRequest request, @RequestBody @Valid LoginCredentials credentials) {
		return customerService.createSession(request, credentials.getEmail(), credentials.getPassword());
	}
	//LOG OUT FOR THE SYSTEM FOR WHOEVER
	@PostMapping("/logOut")
	public String DestroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "user logged out succesfuly from the store";
	}
	
	//OWNER CAN LOGIN TO THE SYSTEM AND MAKE API CALLS ONLY SUIT FOR HIM(SESSION USE CASE)
	@GetMapping("/owner/login")
	public String ownerLogin(HttpServletRequest request, @RequestBody @Valid LoginCredentials credentials) {
		return ownerService.createSession(request, credentials.getEmail(), credentials.getPassword());
	}	

}
