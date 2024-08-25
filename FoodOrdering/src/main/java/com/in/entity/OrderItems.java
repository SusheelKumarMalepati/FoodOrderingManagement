package com.in.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrderItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderItemId;
	@ManyToOne
	@JoinColumn(name="vendor_vendorId",nullable=false)
	private Vendor vendor;
	@ManyToOne
	@JoinColumn(name="foodItems_foodId",nullable=false)
	private FoodItems foodItems;
	private int quantityOrdered;
	private double totalPrice;
	@ManyToOne
	@JoinColumn(name="orders_orderId",nullable=false)
	private Orders orders;
}
