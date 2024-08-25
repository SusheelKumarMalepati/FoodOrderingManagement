package com.in.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Optional<Customer> findCustomerByPhoneNumberAndPassword(Long phoneNumber,String password);
	public boolean existsByPhoneNumber(Long phoneNumber);
}
