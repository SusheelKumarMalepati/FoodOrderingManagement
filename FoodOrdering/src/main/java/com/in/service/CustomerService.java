package com.in.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.in.entity.Customer;

@Component
public interface CustomerService {
	public ResponseEntity<Map<String,Object>> customerLogin(Long phoneNumber,String password);
}
