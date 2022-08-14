package com.store.storevmware.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.store.storevmware.Entity.Customer;
import com.store.storevmware.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public ResponseEntity<Customer> createCustomer(Customer customer) {
		if(customerRepository.getCustomerByEmailAndPassword(customer.getEmail(), customer.getPassword()) ==  null)
		return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
		else
			return new ResponseEntity<>(customer, HttpStatus.EXPECTATION_FAILED);
	}
	
	public String createSession(HttpServletRequest request, String email, String password) {
		
		Customer customer = customerRepository.getCustomerByEmailAndPassword(email, password);
		if(customer != null && request.getSession().getAttribute("USER") == null) {
			request.getSession().setAttribute("USER", customer);
			return "user logged in to the store";
		}
		else
			return "user wasnt able to logging to the store";
			
			
	}
}
