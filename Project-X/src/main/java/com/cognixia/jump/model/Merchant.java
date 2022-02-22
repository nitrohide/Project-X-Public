package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Merchant implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "MerchantID")
	private Long id;
	
	@NotBlank
	(message = "Enter your unique username")
	@Column(unique = true, name = "MerchantUsername")
	private String merchantUsername;
	
	//password of at least one digit, one upper case letter, one special character, no white spaces, and between 8 to 20 characters
	@NotBlank(message = "Password cannot be blank")
	@Pattern(regexp  = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
	@Column(nullable = false, name = "MerchantPassword")
	private String merchantPassword;
	
	@NotBlank(message = "First name cannot be blank")
	@Column(nullable = false, name = "firstName")
	private String firstName;
	
	@NotBlank(message = "Last name cannot be blank")
	@Column(nullable = false, name = "LastName")
	private String lastName;
	
	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
	private List<Product> products;


	public Merchant() {
		this(-1L,  "N/A", "N/A", "N/A","N/A", new ArrayList<Product>());
	}


	public Merchant(Long id, @NotBlank(message = "Enter your unique username") String merchantUsername,
			@NotBlank(message = "Password cannot be blank") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$") String merchantPassword,
			@NotBlank String firstName, @NotBlank(message = "Last name cannot be blank") String lastName,
			List<Product> products) {
		super();
		this.id = id;
		this.merchantUsername = merchantUsername;
		this.merchantPassword = merchantPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.products = products;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMerchantUsername() {
		return merchantUsername;
	}


	public void setMerchantUsername(String merchantUsername) {
		this.merchantUsername = merchantUsername;
	}


	public String getMerchantPassword() {
		return merchantPassword;
	}


	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Merchant [id=" + id + ", merchantUsername=" + merchantUsername + ", merchantPassword="
				+ merchantPassword + ", firstName=" + firstName + ", lastName=" + lastName + ", products=" + products
				+ "]";
	}



		
}
