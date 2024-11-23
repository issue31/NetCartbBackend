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
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ProductDao productDao;
	public List<OrderInputModel> makeOrder(List<OrderInputModel> inputModels,Integer userId) throws UserNotFoundException {
		User user = userDao.findById(userId).orElse(null);
		if(user == null) {
			throw new UserNotFoundException("user not found");
		}
		int price = 0;
		Orders order = new Orders();
		for(OrderInputModel o:inputModels) {
			Product product = productDao.findById(o.getPId()).orElse(null);
			price +=product.getPPrice();
			order.setPrice(price);
		}
		order.setStatus("Booked");
		order.setOrderDate(LocalDate.now());
		order.setUser(user);
		order.setProduct(null);
		order=orderDao.save(order);
		return inputModels;
		}
		public OrderOutputModel cancelOrder(Integer orderId) throws OrderNotFoundException {
			Orders o =orderDao.findById(orderId).orElse(null);
			if(o == null) {
				throw new OrderNotFoundException("order  not found");
			}
			o.setStatus("Cancelled");
			orderDao.save(o);
			OrderOutputModel model = new OrderOutputModel();
			model.setOrderDate(o.getOrderDate());
			model.setOrderId(o.getOrderId());
			model.setPrice(o.getPrice());
			model.setStatus(o.getStatus());
			model.setUserId(o.getUser().getUserId());
			return model;
		}
		public List<OrderOutputModel> getOrderByUserId(Integer userId) throws UserNotFoundException{
			User user = userDao.findById(userId).orElse(null);
			if(user == null) {
				throw new UserNotFoundException("user not found");
			} 
			List<Orders> orders = orderDao.findByUserUserId(userId);
			List<OrderOutputModel> outputModels = new ArrayList<OrderOutputModel>();
			for(Orders o:orders) {
				OrderOutputModel model = new OrderOutputModel();
				model.setOrderDate(o.getOrderDate());
				model.setOrderId(o.getOrderId());
				model.setPrice(o.getPrice());
				model.setStatus(o.getStatus());
				model.setUserId(userId);
				outputModels.add(model);
				}
			return outputModels;
		}
//		@Override
//		public List<OrderOutputModel> getAllOrders() {
//			// TODO Auto-generated method stub
//			List<Orders> orders= orderDao.findAll();
//			List<OrderOutputModel> outputModels = new ArrayList<OrderOutputModel>();
//			for(Orders o:orders) {
//				OrderOutputModel model = new OrderOutputModel();
//				model.setOrderDate(o.getOrderDate());
//				model.setOrderId(o.getOrderId());
//				model.setPrice(o.getPrice());
//				model.setStatus(o.getStatus());
//				model.setUserId(1);
//				outputModels.add(model);
//				}
//			return outputModels;
//			
//		}

}
