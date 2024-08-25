package com.in.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.entity.FoodItems;
import com.in.repository.FoodItemsRepository;

@Service
public class FoodItemsServiceImpl implements FoodItemsService{
	@Autowired
	FoodItemsRepository foodItemsRepository;
	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Map<String,Object>> searchFood(String foodName,String vendorName){
		List<FoodItems> foodItems=foodItemsRepository.getByFoodNameAndVendor_vendorName(foodName,vendorName);
		Map<String,Object> response=new HashMap<>();
		if(foodItems.isEmpty()) {
			response.put("statusCode",HttpStatus.NOT_FOUND.value());
			response.put("statusMessage", "No Record Found");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.NOT_FOUND
					);
		}
//		int n=foodItems.size();
//		List<String> food =new ArrayList<String>();
//		for(int i=0;i<n;i++) {
//			food.add("foodName:"+foodItems.get(i).getFoodName()+",\r\n"+"\nvendorName:"+foodItems.get(i).getVendor().getVendorName()+",\r\n"+
//					"\nprice:"+foodItems.get(i).getPrice()+",\r\n"+"\nquantityAvailable:"+foodItems.get(i).getQuantityAvailable());
//		}
		response.put("statusCode",HttpStatus.FOUND.value());
		response.put("statusMessage", "Successfully Fetched");
		response.put("data", foodItems);
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.FOUND
				);
	}
}
