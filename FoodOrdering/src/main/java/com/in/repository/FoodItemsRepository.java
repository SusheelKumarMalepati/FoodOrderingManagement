package com.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.entity.FoodItems;

@Repository
public interface FoodItemsRepository extends JpaRepository<FoodItems, Long>{
	public List<FoodItems> getByFoodNameAndVendor_vendorName(String foodName, String vendorName);
}
