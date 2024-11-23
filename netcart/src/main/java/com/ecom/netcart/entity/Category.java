package com.ecom.netcart.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;
@Data
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryTitle;
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();

	
	
	
	
	
}
