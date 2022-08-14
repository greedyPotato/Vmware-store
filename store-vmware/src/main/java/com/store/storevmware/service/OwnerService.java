package com.store.storevmware.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.storevmware.Entity.Customer;
import com.store.storevmware.Entity.Owner;
import com.store.storevmware.repository.OwnerRepository;

@Service
public class OwnerService {

	@Autowired
	OwnerRepository ownerRepository;
	
	public ResponseEntity<Owner> createOwner(Owner owner) {
		if(ownerRepository.getOwnerByEmailAndPassword(owner.getEmail(), owner.getPassword()) == null)
		return new ResponseEntity<Owner>(ownerRepository.save(owner), HttpStatus.CREATED);
		else
			return new ResponseEntity<Owner>(owner, HttpStatus.EXPECTATION_FAILED);
	}

	public String createSession(HttpServletRequest request, String email, String password) {
		Owner owner = ownerRepository.getOwnerByEmailAndPassword(email, password);
				if(owner != null && request.getSession().getAttribute("OWNER") == null) {
					request.getSession().setAttribute("OWNER", owner);
					return "user logged in to the store";
				}
				else
					return "user wasnt able to logging to the store";
	
}

}

	
	