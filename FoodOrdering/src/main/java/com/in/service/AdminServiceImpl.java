package com.in.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.entity.FoodItems;
import com.in.entity.Customer;
import com.in.entity.Vendor;
import com.in.exception.PhoneNumberAlreadyExistsException;
import com.in.repository.FoodItemsRepository;
import com.in.repository.CustomerRepository;
import com.in.repository.VendorRepository;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	VendorRepository vendorRepository;
	@Autowired
	FoodItemsRepository foodItemsRepository;
	@Override
	public ResponseEntity<Map<String,Object>> createCustomer(String customerName,String password,Long phoneNumber,String address){
		Map<String,Object> response=new HashMap<>();
		if (customerRepository.existsByPhoneNumber(phoneNumber)) {
			response.put("statusCode",HttpStatus.CONFLICT.value());
			response.put("statusMessage", "Customer Already Logged with same mobile number");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.CONFLICT
					);
	    }
		Customer customer=new Customer();
		customer.setCustomerName(customerName);
		customer.setPassword(password);
		customer.setPhoneNumber(phoneNumber);
		customer.setAddress(address);
		customer=customerRepository.save(customer);
		response.put("statusCode",HttpStatus.OK.value());
		response.put("statusMessage", "Customer Created Successfully");
		response.put("customerId", customer.getCustomerId());
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.OK
				);
	}
	@Override
	public ResponseEntity<Map<String,Object>> createVendor(String vendorName,Long phoneNumber){
		Map<String,Object> response=new HashMap<>();
		if (vendorRepository.existsByPhoneNumber(phoneNumber)) {
			response.put("statusCode",HttpStatus.CONFLICT.value());
			response.put("statusMessage", "Vendor Already Logged with same mobile number");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.CONFLICT
					);
	    }
		Vendor vendor=new Vendor();
		vendor.setVendorName(vendorName);
		vendor.setPhoneNumber(phoneNumber);
		vendor=vendorRepository.save(vendor);
		response.put("statusCode",HttpStatus.OK.value());
		response.put("statusMessage", "Vendor Created Successfully");
		response.put("vendorId", vendor.getVendorId());
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.OK
				);
	}
	@Override
	public ResponseEntity<Map<String,Object>> createFood(String foodName, int quantityAvailable, int price, Long vendorId){
		FoodItems food=new FoodItems();
		food.setFoodName(foodName);
		food.setQuantityAvailable(quantityAvailable);
		food.setPrice(price);
		food.setVendor(vendorRepository.getReferenceById(vendorId));
		food=foodItemsRepository.save(food);
		Map<String,Object> response=new HashMap<>();
		response.put("statusCode",HttpStatus.OK.value());
		response.put("statusMessage", "Food Created Successfully");
		response.put("FoodId", food.getFoodId());
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.OK
				);
	}
	@Override
	public ResponseEntity<Map<String,Object>> updateQuantity(Long foodId, int quantityAvailable){
		FoodItems food=foodItemsRepository.getReferenceById(foodId);
		food.setQuantityAvailable(quantityAvailable);
		food=foodItemsRepository.save(food);
		Map<String,Object> response=new HashMap<>();
		response.put("statusCode",HttpStatus.OK.value());
		response.put("statusMessage", "Food Quantity Upadted Successfully");
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.OK
				);
	}
	@Override
	public ResponseEntity<Map<String,Object>> updatePrice(Long foodId, int price){
		FoodItems food=foodItemsRepository.getReferenceById(foodId);
		food.setPrice(price);
		food=foodItemsRepository.save(food);
		Map<String,Object> response=new HashMap<>();
		response.put("statusCode",HttpStatus.OK.value());
		response.put("statusMessage", "Food Price Upadted Successfully");
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.OK
				);
	}

}
