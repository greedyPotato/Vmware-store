package com.store.storevmware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.store.storevmware.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	
    public Customer getCustomerByEmailAndPassword(String email,String password);
}