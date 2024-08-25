package com.in.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.entity.Customer;
import com.in.entity.FoodItems;
import com.in.entity.UpdatePriceAndQuantity;
import com.in.entity.Vendor;
import com.in.service.AdminService;

@RestController
@RequestMapping(path="/admin/v1")
public class AdminController {
	@Autowired
	AdminService adminService;
	@PostMapping(path="/add/customer")
	public ResponseEntity<Map<String,Object>> createcustomer(@RequestBody Customer customer){
		return adminService.createCustomer(customer.getCustomerName(),customer.getPassword(),customer.getPhoneNumber(),customer.getAddress());
	}
	@PostMapping(path="/add/vendor")
	public ResponseEntity<Map<String,Object>> createVendor(@RequestBody Vendor vendor){
		return adminService.createVendor(vendor.getVendorName(),vendor.getPhoneNumber());
	}
	@PostMapping(path="/add/food")
	public ResponseEntity<Map<String,Object>> createFood(@RequestBody FoodItems foodItems){
		return adminService.createFood(foodItems.getFoodName(),foodItems.getQuantityAvailable(),foodItems.getPrice(),foodItems.getVendor().getVendorId());
	}
	@PutMapping(path="/update/quantity")
	public ResponseEntity<Map<String,Object>> updateQuantity(@RequestBody UpdatePriceAndQuantity updatedDetails){
		return adminService.updateQuantity(updatedDetails.getFoodId(),updatedDetails.getQuantityAvailable());
	}
	@PutMapping(path="/update/price")
	public ResponseEntity<Map<String,Object>> updatePrice(@RequestBody UpdatePriceAndQuantity updatedDetails){
		return adminService.updatePrice(updatedDetails.getFoodId(),updatedDetails.getPrice());
	}
}