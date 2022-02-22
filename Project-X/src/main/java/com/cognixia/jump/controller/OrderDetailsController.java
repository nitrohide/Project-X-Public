package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.OrderDetails;
import com.cognixia.jump.repository.OrderDetailsRepository;

@RequestMapping("/api")
@RestController
public class OrderDetailsController {
	
	@Autowired
	OrderDetailsRepository service;
	
	@GetMapping("/orders")
	public List<OrderDetails> getAllOrderDetails(){
		
		return service.findAll();
	}
	
	@GetMapping("/order/{id}")
	public OrderDetails getOrderDetailById(@PathVariable long id) {
		
		return service.getById(id);
	}
	
	@PostMapping("/order/add")
	public ResponseEntity<String> addOrderDetails(@Valid @RequestBody OrderDetails orderDetails){
		
		if(service.existsById(orderDetails.getId())) {
			return ResponseEntity.status(400).body("Product with " + orderDetails.getId() + " already exists :(");
		} else {
			OrderDetails created = service.save(orderDetails);
			return ResponseEntity.status(201)
					.body("SUCCESS: Created New Order Details with 'id' ==> " + created.getId());
		}
	}
	
	@PutMapping("/order/update")
	public void updateOrderDetails(@RequestBody OrderDetails orderDetails) throws ResourceNotFoundException {
		
		if (service.existsById(orderDetails.getId()) ) {
			service.save(orderDetails);
		}
		else {
			throw new ResourceNotFoundException("Customer with id = " + orderDetails.getId()
					+ " was not found and could not be updated");
		}
		
	}
	
	@DeleteMapping("/order/delete/{id}")
	 public ResponseEntity<String> deleteOrderDetails(@PathVariable Long id){
		 if(service.existsById(id)) {
	    		service.deleteById(id);
				return ResponseEntity.status(200)
						.body("SUCCESS: Deleted Order Details with id " + id);
			} else {
				return ResponseEntity.status(400).body("FAIL: The Order Details With id " +
			            id + " does not exist :(");
			}
	 }

}
