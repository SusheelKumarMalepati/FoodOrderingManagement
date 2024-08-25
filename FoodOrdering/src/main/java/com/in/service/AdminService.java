package com.in.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {
	public ResponseEntity<Map<String,Object>> createCustomer(String customerName,String password,Long phoneNumber,String address);
	public ResponseEntity<Map<String, Object>> createVendor(String vendorName,Long PhoneNumber);
	public ResponseEntity<Map<String,Object>> createFood(String foodName, int quantityAvailable, int price, Long vendorId);
	public ResponseEntity<Map<String,Object>> updateQuantity(Long foodId, int quantityAvailable);
	public ResponseEntity<Map<String,Object>> updatePrice(Long foodId, int price);
}
