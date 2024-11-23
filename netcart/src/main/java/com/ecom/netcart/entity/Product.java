package com.ecom.netcart.entity;



import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pId;
	private String pName;
	@Column
	private String pDesc;
	
	private Integer pPrice;

	@JoinColumn(name = "categoryId")
	@ManyToOne
	private Category category;
	@ManyToMany(mappedBy = "product",fetch = FetchType.EAGER)
	private List<Orders> order;
	
	
	
	
	
	
}