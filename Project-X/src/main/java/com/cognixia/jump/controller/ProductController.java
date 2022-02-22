package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class ProductController {
	
	@Autowired
	ProductRepository service;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		
		return service.findAll();
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable long id) throws ResourceNotFoundException {
		
		Optional<Product> found = service.findById(id);
		
		if(!found.isPresent()) {
			throw new ResourceNotFoundException("Product with ID: " + id + " was not found.");
		}
		
		return found.get();
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/product/category/{category}")
	public List<Product> productByCategory(@PathVariable String category) {
	
		return service.findByCategory(category);
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/product/price/max/{price}")
	public List<Product> getProductsWithMaxPrice(@PathVariable float price){
		
		return service.getProductsWithMaxPrice(price);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/product/add")
	public ResponseEntity<String> addProduct(@Valid @RequestBody Product product){
		
		if(service.existsById(product.getProductID())) {
			return ResponseEntity.status(400).body("Product with " + product.getProductID() + " already exists :(");
		} else {
			Product created = service.save(product);
			return ResponseEntity.status(201)
					.body("SUCCESS: Created New Product with 'id' ==> " + created.getProductID());
		}
		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	 @PutMapping("/product/update")
     public void updateProduct(@RequestBody Product product) throws ResourceNotFoundException {
		
		if (service.existsById(product.getProductID()) ) {
			service.save(product);
		}
		else {
			throw new ResourceNotFoundException("Customer with id = " + product.getProductID()
					+ " was not found and could not be updated");
		}
		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	 @DeleteMapping("/product/delete/{id}")
	 public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		 if(service.existsById(id)) {
	    		service.deleteById(id);
				return ResponseEntity.status(200)
						.body("SUCCESS: Deleted product with id " + id);
			} else {
				return ResponseEntity.status(400).body("FAIL: The Product With id " +
			            id + " does not exist :(");
			}
	 }
//	 
//	 @GetMapping("/error")
//		public void getError() throws Exception {
//			throw new Exception("This request returns an error");
//		}
//	
	
	
	
}
