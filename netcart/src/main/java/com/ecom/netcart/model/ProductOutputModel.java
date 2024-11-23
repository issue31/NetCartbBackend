package com.ecom.netcart.model;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class ProductOutputModel {
	private Integer pId;
	private String pName;
	private String pDesc;
	private Integer pPrice;
	//private Integer pQuantity;
	
}
