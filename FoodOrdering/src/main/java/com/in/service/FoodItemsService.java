package com.in.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface FoodItemsService {
	public ResponseEntity<Map<String,Object>> searchFood(String foodName,String vendorName);
}
