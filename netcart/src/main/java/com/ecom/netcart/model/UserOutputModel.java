package com.ecom.netcart.model;

import lombok.Data;

@Data
public class UserOutputModel {

	private Integer userId;
    private String username;
    private String email;
    private String address;
}
