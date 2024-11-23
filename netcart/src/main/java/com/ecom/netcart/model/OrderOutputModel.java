package com.ecom.netcart.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderOutputModel {
	private Integer orderId;
	private Integer price;
	private LocalDate orderDate;
	
	private Integer userId;
	private String status;
}
