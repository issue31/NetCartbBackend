package com.ecom.netcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.netcart.entity.Orders;

public interface OrderDao extends JpaRepository<Orders, Integer>{

	//@Query("select o from Orders o where o.user.userId =:userId")
	//List<Orders> findByUserId(Integer userId);

	List<Orders> findByUserUserId(Integer userId);

}
