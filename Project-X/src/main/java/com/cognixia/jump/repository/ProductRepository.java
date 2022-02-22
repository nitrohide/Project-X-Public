package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
  
  List<Product> findByCategory(String category);
  
  //Custom Query
  @Query("select c from Product c where c.price < ?1")
  List<Product> getProductsWithMaxPrice(Float price);
  

}
