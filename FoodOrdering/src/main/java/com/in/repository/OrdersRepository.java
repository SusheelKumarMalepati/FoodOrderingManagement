package com.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
	public List<Orders> findByCustomer_customerId(Long userId);
}
