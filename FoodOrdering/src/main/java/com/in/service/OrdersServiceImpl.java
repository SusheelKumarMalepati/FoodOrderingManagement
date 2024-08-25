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

import com.in.entity.Orders;
import com.in.repository.OrdersRepository;

@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	OrdersRepository ordersRepository;
	@SuppressWarnings("null")
	public ResponseEntity<Map<String,Object>> viewHistory(Long customerId){
		List<Orders> orders=ordersRepository.findByCustomer_customerId(customerId);
		Map<String,Object> response=new HashMap<>();
		if(orders.isEmpty()) {
			response.put("statusCode",HttpStatus.NOT_FOUND.value());
			response.put("statusMessage", "No Record Found");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.NOT_FOUND
					);
		}
		List<Map<String,Map<String,String>>> order=new ArrayList<Map<String,Map<String,String>>>();
		int n=orders.size();
		for(int i=0;i<n;i++) {
			Map<String,Map<String,String>> obj=new HashMap<>();
			Map<String,String> obj1=new HashMap<>();
			obj1.put("FoodName:"+orders.get(i).getFoodItems().getFoodName(),"purchaseDate:"+orders.get(i).getPurchasedDate());
			obj.put("orderId:"+orders.get(i).getOrderId(),obj1);
			order.add(obj);
		}
		response.put("statusCode",HttpStatus.FOUND.value());
		response.put("statusMessage", "Order Fetched Successfully");
		response.put("data", order);
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.FOUND
				);
	}
}
