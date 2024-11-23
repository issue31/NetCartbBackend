package com.ecom.netcart.entity;

import java.time.LocalDate;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Orders {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Integer price;
	private LocalDate orderDate;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> product;
	@ManyToOne
	@ToString.Exclude           //we want to hide the details of the user by using @ToString.Exclude
	@JoinColumn(name = "userId")
	private User user;
	private String status;
}
