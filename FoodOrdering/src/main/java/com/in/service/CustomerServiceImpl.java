package com.in.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.entity.Customer;
import com.in.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	@Override
	public ResponseEntity<Map<String,Object>> customerLogin(Long phoneNumber,String password) {
		Optional<Customer> customer=customerRepository.findCustomerByPhoneNumberAndPassword(phoneNumber,password);
		Map<String,Object> response=new HashMap<>();

		if(customer.isPresent()) {
			response.put("statusCode",HttpStatus.OK.value());
			response.put("statusMessage", "LOGGED SUCCESSFULLY");
			return new ResponseEntity<>(
					response,
					new HttpHeaders(),
					HttpStatus.OK
					);
		}
		response.put("statusCode",HttpStatus.NOT_FOUND.value());
		response.put("statusMessage", "Invalid contact number or Password");
		return new ResponseEntity<>(
				response,
				new HttpHeaders(),
				HttpStatus.NOT_FOUND
				);
	}
}
