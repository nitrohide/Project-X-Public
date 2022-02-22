package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{
	
	
	@Query(value = "select * from merchant where merchant_username=?1 and merchant_password=?2", nativeQuery=true)
	Merchant findByMerchantUsername(String username, String password);
}
