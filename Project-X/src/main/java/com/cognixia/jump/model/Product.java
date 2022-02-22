package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Product implements Serializable {
	
	
	private static final long serialVersionUID = -2281514348113470467L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ProductID")
	private Long productID;
	
    @NotNull
    @Column(nullable = false, name = "ProductName")
	private String productName;
	
    @NotNull
    //@Column(columnDefinition = "price default 0.00")
    @Range(min = 0, max = 200000)
    @Column(nullable = false, name = "ProductPrice")
	private Float price;
	
    @NotNull
    @Column(nullable = false, name = "ProductCategory")
	private String category;
	
    @NotNull
    @Column(nullable = false, name = "ProductDescription")
	private String description;
	
	@NotNull
	@Column(nullable = false, name = "ProductQuantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "MerchantID", referencedColumnName = "MerchantID")
	private Merchant merchant;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<OrderDetails> orders;
	
	public Product() {
		this(-1L, "N/A", 0.00F , "N/A", "N/A", 0);
	}

	public Product(Long productID, @NotNull String productName, @Range(min = 0, max = 200000) Float price, @NotNull String category,
			@NotNull String description, @NotNull int quantity) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.category = category;
		this.description = description;
		this.quantity = quantity;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", price=" + price + ", category="
				+ category + ", description=" + description + ", quantity=" + quantity + ", merchant=" + merchant + "]";
	}

}
