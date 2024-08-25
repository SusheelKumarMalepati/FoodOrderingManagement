package com.in.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface FoodOrderService {
	public ResponseEntity<Map<String,Object>> orderFood(Long customerId,Long vendorId,Long foodId,int quantityOrdered);
}
