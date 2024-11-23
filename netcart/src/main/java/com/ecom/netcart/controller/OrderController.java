package com.ecom.netcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.netcart.exception.OrderNotFoundException;
import com.ecom.netcart.exception.UserNotFoundException;
import com.ecom.netcart.model.CategoryOutputModel;
import com.ecom.netcart.model.OrderInputModel;
import com.ecom.netcart.model.OrderOutputModel;
import com.ecom.netcart.service.OrderService;

@RestController
@CrossOrigin("*")
public class OrderController {
@Autowired
OrderService orderService;
Logger logger = LoggerFactory.getLogger(OrderController.class);
@PostMapping("/makeorder/{userId}")
public ResponseEntity<List<OrderInputModel>>  makeOrder(@RequestBody List<OrderInputModel> inputModels,@PathVariable("userId")Integer userId) throws UserNotFoundException {
	logger.info("makeOrder() method started");
	List<OrderInputModel> order= orderService.makeOrder(inputModels, userId);
	logger.info("makeOrder={}",order);
	logger.info("makeOrder() method ended");
	return new ResponseEntity<List<OrderInputModel>>(order,HttpStatus.OK);
}
@PutMapping("/cancelOrder/{orderId}")
public ResponseEntity<OrderOutputModel> cancelOrder(@PathVariable("orderId")Integer orderId) throws OrderNotFoundException {
	logger.info("cancelOrder() method started");
	OrderOutputModel deleteorder = orderService.cancelOrder(orderId);
	logger.info("cancelOrder={}",deleteorder);
	logger.info("cancelOrder() method ended");
	return new ResponseEntity<OrderOutputModel>(deleteorder,HttpStatus.OK);
}
//@GetMapping("/getAllOrders")
//public ResponseEntity<List<OrderOutputModel>>  getAllOrders(){
//	logger.info("getAllOrders() method started");
//	List<OrderOutputModel> orderOutputModels = orderService.getAllOrders();
//	logger.info("getAllOrders={}",orderOutputModels);
//	logger.info("getAllOrders() method ended");
//	return  new ResponseEntity<List<OrderOutputModel>>(orderOutputModels,HttpStatus.OK);
//
//}
@GetMapping("/getAllOrders/{userId}")
public ResponseEntity<List<OrderOutputModel>> getOrderByUserId(@PathVariable("userId") Integer userId) throws UserNotFoundException{
	logger.info("OrderByUserId() method started");
	List<OrderOutputModel> list = orderService.getOrderByUserId(userId);
	logger.info("OrderByUserId={}",list);
	logger.info("OrderByUserId() method ended");
	return new ResponseEntity<List<OrderOutputModel>>(list,HttpStatus.OK);
}
}