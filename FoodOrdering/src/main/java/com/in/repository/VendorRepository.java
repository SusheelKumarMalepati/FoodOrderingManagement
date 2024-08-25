package com.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{
	public boolean existsByPhoneNumber(Long phoneNumber);
}
