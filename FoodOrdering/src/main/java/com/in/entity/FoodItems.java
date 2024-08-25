package com.in.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long foodId;
	private String foodName;
	private int quantityAvailable;
	private int price;
	@ManyToOne
	@JoinColumn(name="vendor_vendorId",nullable=false)
	private Vendor vendor;
}
