package com.ecom.netcart.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.netcart.entity.Orders;
import com.ecom.netcart.entity.Product;
import com.ecom.netcart.entity.User;
import com.ecom.netcart.exception.OrderNotFoundException;
import com.ecom.netcart.exception.UserNotFoundException;
import com.ecom.netcart.model.OrderInputModel;
import com.ecom.netcart.model.OrderOutputModel;
import com.ecom.netcart.repository.OrderDao;
import com.ecom.netcart.repository.ProductDao;
import com.ecom.netcart.repository.UserDao;

@Service
public interface OrderService {

		public List<OrderInputModel> makeOrder(List<OrderInputModel> inputModels,Integer userId) throws UserNotFoundException;
		public OrderOutputModel cancelOrder(Integer orderId) throws OrderNotFoundException;
		public List<OrderOutputModel> getOrderByUserId(Integer userId) throws UserNotFoundException;
		//public List<OrderOutputModel>  getAllOrders();
	
}

