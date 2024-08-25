package com.in.entity;

import lombok.Data;

@Data
public class UpdatePriceAndQuantity {
	private Long foodId;
	private int quantityAvailable;
	private int price;
}
