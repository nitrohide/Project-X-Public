package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

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

import com.cognixia.jump.exception.InvalidPasswordException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.repository.CustomerRepository;

@RequestMapping("/api")
@RestController
public class CustomerController {

	@Autowired
	CustomerRepository service;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		
		return service.findAll();		
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable long id)  throws ResourceNotFoundException{
		
		Optional<Customer> found = service.findById(id);
		
		if(!found.isPresent()) {
			throw new ResourceNotFoundException("Customer with id = " + id + " was not found");
		}
		
		return found.get();
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/add/customer")
	public void addCustomer(@RequestBody Customer customer) throws InvalidPasswordException {
		
		System.out.println(customer);
		if(!customer.getCustomerPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
			throw new InvalidPasswordException(customer.getCustomerPassword());
		}
		
		service.save(customer);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/update/customer")
	public void updateCustomer(@RequestBody Customer customer) throws ResourceNotFoundException {
		
		if (service.existsById(customer.getId()) ) {
			service.save(customer);
		}
		else {
			throw new ResourceNotFoundException("Customer with id = " + customer.getId()
					+ " was not found and could not be updated");
		}
		
	}
	
	@DeleteMapping("/customer/delete/{id}")
	public ResponseEntity<String> delteCustomer(@PathVariable Long id){
		
		if(service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.status(200)
					.body("SUCCESS: Deleted Customer with id " + id);
		}
		else {
			return ResponseEntity.status(400)
					.body("FAIL: The Customer with id " + id + " does not exist!");
		}
	}
	
	@GetMapping("/error")
	public void getError() throws Exception {
		throw new Exception("This request returns an error");
	}
}
