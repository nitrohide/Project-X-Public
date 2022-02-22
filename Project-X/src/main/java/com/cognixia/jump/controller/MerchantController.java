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
import com.cognixia.jump.model.Merchant;
import com.cognixia.jump.repository.MerchantRepository;

@RequestMapping("/api")
@RestController
public class MerchantController {

	@Autowired
	MerchantRepository service;
	
	@GetMapping("/merchants")
	public List<Merchant> getAllMerchants(){
		
		return service.findAll();		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/merchant/{id}")
	public Merchant getMerchantById(@PathVariable long id)  throws ResourceNotFoundException{
		
		Optional<Merchant> found = service.findById(id);
		
		if(!found.isPresent()) {
			throw new ResourceNotFoundException("Merchant with id = " + id + " was not found");
		}
		
		return found.get();
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/add/merchant")
	public void addMerchant(@RequestBody Merchant merchant) throws InvalidPasswordException {
		
		
		if(!merchant.getMerchantPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
			throw new InvalidPasswordException(merchant.getMerchantPassword());
		}
		
		service.save(merchant);
		
	}
	
	@PutMapping("/update/merchant")
	public void updateMerchant(@RequestBody Merchant merchant) throws ResourceNotFoundException {
		
		if (service.existsById(merchant.getId()) ) {
			service.save(merchant);
		}
		else {
			throw new ResourceNotFoundException("Merchant with id = " + merchant.getId()
					+ " was not found and could not be updated");
		}
		
	}
	
	@DeleteMapping("/merchant/delete/{id}")
	public ResponseEntity<String> deleteMerchant(@PathVariable Long id){
		
		if(service.existsById(id)) {
			service.deleteById(id);
			return ResponseEntity.status(200)
					.body("SUCCESS: Deleted Merchant with id " + id);
		}
		else {
			return ResponseEntity.status(400)
					.body("FAIL: The Merchant with id " + id + " does not exist!");
		}
	}
	
}
