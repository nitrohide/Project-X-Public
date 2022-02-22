package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long>{

	@Query(value = "select * from customer where customer_username=?1 and customer_password=?2", nativeQuery=true)
	Customer findByCustomerUsername(String username, String password);
	
}
