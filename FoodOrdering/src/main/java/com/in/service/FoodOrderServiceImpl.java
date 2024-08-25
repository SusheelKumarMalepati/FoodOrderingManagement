package com.in.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.entity.FoodItems;
import com.in.entity.OrderItems;
import com.in.entity.Orders;
import com.in.repository.FoodItemsRepository;
import com.in.repository.FoodOrderRepository;
import com.in.repository.OrdersRepository;
import com.in.repository.CustomerRepository;
import com.in.repository.VendorRepository;

@Service
public class FoodOrderServiceImpl implements FoodOrderService{
	@Autowired
	FoodOrderRepository foodOrderRepository;
	@Autowired
	VendorRepository vendorRepository;
	@Autowired
	FoodItemsRepository foodItemsRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	OrdersRepository ordersRepository;
	@Override
	public ResponseEntity<Map<String,Object>> orderFood(Long customerId,Long vendorId,Long foodId,int quantityOrdered){
		OrderItems order=new OrderItems();
		Map<String,Object> response=new HashMap<>();
		if(!vendorRepository.existsById(vendorId)) {
			response.put("statusCode",HttpStatus.FORBIDDEN.value());
			response.put("statusMessage", "INVALID VENDOR ID");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.FORBIDDEN
					);
		}
		order.setVendor(vendorRepository.getReferenceById(vendorId));
		FoodItems foodItems = foodItemsRepository.findById(foodId).orElse(null);
		if(foodItems==null) {
			response.put("statusCode",HttpStatus.FORBIDDEN.value());
			response.put("statusMessage", "INVALID Food ID");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.FORBIDDEN
					);
		}
		order.setFoodItems(foodItems);
		if(foodItems.getQuantityAvailable()<quantityOrdered) {
			response.put("statusCode",HttpStatus.FORBIDDEN.value());
			response.put("statusMessage", "INSUFFICIENT FOOD QUANTITY");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.FORBIDDEN
					);
		}
		order.setQuantityOrdered(quantityOrdered);

		foodItems.setQuantityAvailable(foodItemsRepository.getReferenceById(foodId).getQuantityAvailable()-quantityOrdered);
		foodItems=foodItemsRepository.save(foodItems);
		Orders orders=new Orders();
		orders.setCustomer(customerRepository.getReferenceById(customerId));
		orders.setFoodItems(foodItemsRepository.getReferenceById(foodId));
		orders.setPurchasedDate(LocalDate.now());
		orders=ordersRepository.save(orders);
		order.setTotalPrice(quantityOrdered*foodItems.getPrice());
		order.setOrders(orders);
		OrderItems order1=foodOrderRepository.save(order);
		if(order1!=null) {
			response.put("statusCode",HttpStatus.ACCEPTED.value());
			response.put("statusMessage", "SUCCESSFULLY ORDERED");
			response.put("data", "orderId:"+order1.getOrderItemId()+"   "+"Total Price:"+order1.getTotalPrice());
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.ACCEPTED
					);
		}
		response.put("statusCode",HttpStatus.FORBIDDEN.value());
		response.put("statusMessage", "Cannot Order Food");
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.FORBIDDEN
				);
	}
}