package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetails implements Serializable{

	private static final long serialVersionUID = -5264672180093497944L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "OrderID")
	private Long id;
	
	@Column(nullable = false, name = "QuantityOrder", columnDefinition = "integer default 0")
	private int quantityOrder;
	
	@Column(nullable = false, name = "PriceOfOrder", columnDefinition = "integer default 0")
	private float totalPriceOfOrder;
	
	@ManyToOne
	@JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
	private Customer customer;

	public OrderDetails() {
		this(-1L, 0, 0.00F);
	}

	public OrderDetails(Long id, int quantityOrder, Float totalPriceOfOrder) {
		super();
		this.id = id;
		this.quantityOrder = quantityOrder;
		this.totalPriceOfOrder = totalPriceOfOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public float getTotalPriceOfOrder() {
		return totalPriceOfOrder;
	}

	public void setTotalPriceOfOrder(float totalPriceOfOrder) {
		this.totalPriceOfOrder = totalPriceOfOrder;
	}

	//Only need setProduct
	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", quantityOrder=" + quantityOrder + ", totalPriceOfOrder="
				+ totalPriceOfOrder + ", product=" + product + "]";
	}

}
