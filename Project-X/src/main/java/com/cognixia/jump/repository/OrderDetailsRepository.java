package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

}
