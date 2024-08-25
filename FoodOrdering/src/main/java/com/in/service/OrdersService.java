package com.in.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface OrdersService {
	public ResponseEntity<Map<String,Object>> viewHistory(Long customerId);
}
