package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.cognixia.jump.model.Login;
import com.cognixia.jump.model.Merchant;
import com.cognixia.jump.repository.CustomerRepository;
import com.cognixia.jump.repository.MerchantRepository;

@RequestMapping("/api")
@RestController
public class LoginController {

	
	@Autowired
	CustomerRepository customerService;
	
	@Autowired
	MerchantRepository merchantService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value="/login/customer" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomerLogin(@RequestBody Login userLogin)  throws ResourceNotFoundException{
		
		Customer found = customerService.findByCustomerUsername(userLogin.getUsername(),userLogin.getPassword());
		
		if (found!=null) {
			return found;
		}else {
			throw new ResourceNotFoundException("invalid login");
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value="/login/merchant" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Merchant getMerchantLogin(@RequestBody Login userLogin)  throws ResourceNotFoundException{
		System.out.println(userLogin.getUsername());
		System.out.println(userLogin.getPassword());

		Merchant found = merchantService.findByMerchantUsername(userLogin.getUsername(),userLogin.getPassword());
		if (found!=null) {
			return found;
		}else {
			throw new ResourceNotFoundException("invalid login");
		}
	}
	
}