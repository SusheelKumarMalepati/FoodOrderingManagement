package com.in.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vendor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long vendorId;
	private String vendorName;
	@Column(unique=true)
	private Long phoneNumber;
}
