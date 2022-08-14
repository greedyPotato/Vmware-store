package com.store.storevmware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.storevmware.Entity.Customer;
import com.store.storevmware.Entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {

	Owner getOwnerByEmailAndPassword(String email, String password);

	
}
