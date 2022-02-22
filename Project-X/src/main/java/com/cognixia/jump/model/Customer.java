package com.cognixia.jump.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Customer implements Serializable{
	

	private static final long serialVersionUID = 6576434186036358131L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "CustomerID")
	private Long id;
	
	@NotBlank(message = "Username cannot be blank")
	@Column(unique = true, name = "CustomerUsername")
	private String customerUsername;
	
	//password of at least one digit, one upper case letter, one special character, no white spaces, and between 8 to 20 characters
	@NotBlank(message = "Password cannot be blank")
	@Pattern(regexp  = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
	@Column(nullable = false, name = "CustomerPassword")
	private String customerPassword;
	
	@NotBlank(message = "First name cannot be blank")
	@Column(nullable = false, name = "FirstName")
	private String firstName;
	
	@NotBlank(message = "Last name cannot be blank")
	@Column(nullable = false, name = "LastName")
	private String lastName;
	
	public Customer() {
		this(-1L,"N/A", "N/A", "N/A", "N/A");
	}

	public Customer(Long id, String customerUsername, String customerPassword, String firstName, String lastName) {
		super();
		this.id=id;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerUsername=" + customerUsername + ", customerPassword="
				+ customerPassword + ", firstName=" + firstName + ", lastName=" + lastName + ", orderDetails="
				 + "]";
	}
		
}
