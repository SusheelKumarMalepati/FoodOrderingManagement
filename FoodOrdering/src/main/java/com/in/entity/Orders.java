package com.in.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	@ManyToOne
	@JoinColumn(name="customer_customerId",nullable=false)
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="foodItems_foodId",nullable=false)
	private FoodItems foodItems;
	private LocalDate purchasedDate;
}
