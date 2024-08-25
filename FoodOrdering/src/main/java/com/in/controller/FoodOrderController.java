package com.in.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.service.FoodItemsService;
import com.in.service.FoodOrderService;
import com.in.service.OrdersService;
import com.in.entity.Customer;
import com.in.entity.FoodItems;
import com.in.entity.FoodOrderRequest;
import com.in.entity.OrderItems;
import com.in.service.CustomerService;

@RestController
@RequestMapping(path="/api/v1")
public class FoodOrderController {
	@Autowired
	FoodItemsService foodItemsService;
	@Autowired
	FoodOrderService foodOrderService;
	@Autowired
	CustomerService customerService;
	@Autowired
	OrdersService ordersService;
	@PostMapping(path="/login")
	public ResponseEntity<Map<String,Object>> userLogin(@RequestBody Customer customer){
		return customerService.customerLogin(customer.getPhoneNumber(),customer.getPassword());
	}
	@GetMapping(path="/food")
	public ResponseEntity<Map<String,Object>> searchFood(@RequestParam String foodName,@RequestParam String vendorName){
		return foodItemsService.searchFood(foodName,vendorName);
	}
	@PostMapping(path="/order/foods")
	public ResponseEntity<Map<String,Object>> orderFood(@RequestBody FoodOrderRequest foodOrderRequest){
		return foodOrderService.orderFood(foodOrderRequest.getCustomerId(),foodOrderRequest.getVendorId(),
				foodOrderRequest.getFoodId(),foodOrderRequest.getQuantityOrdered());
	}
	@GetMapping(path="/order/food/histories")
	public ResponseEntity<Map<String,Object>> viewHistory(@RequestParam Long customerId){
		return ordersService.viewHistory(customerId);
	}
}
