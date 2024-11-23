package com.ecom.netcart.model;

import lombok.Data;

@Data
public class ErrorResponse {

	private Integer errorCode;
	private String errorMessage;
}
