package com.in.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodOrderRequest {
    private Long customerId;
    private Long vendorId;
    private Long foodId;
    private int quantityOrdered;
}
