package com.ecom.netcart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.netcart.exception.InvalidLoginException;
import com.ecom.netcart.exception.InvalidRegistrationException;
import com.ecom.netcart.model.UserInputModel;
import com.ecom.netcart.model.UserOutputModel;
import com.ecom.netcart.service.UserService;
import com.ecom.netcart.service.UserServiceImpl;


@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);
	@PostMapping("/adduser")
	public ResponseEntity<UserOutputModel>  addUser(@RequestBody UserInputModel userinputmodel) throws InvalidRegistrationException  {
		logger.info("addUser() method started");
		UserOutputModel userOutputModel=userService.addUser(userinputmodel);
		logger.info("addUser={}",userOutputModel);
		logger.info("addUser() method ended");
    	return new ResponseEntity<UserOutputModel>(userOutputModel,HttpStatus.OK);
}
	@GetMapping("/login/{emailId}")
	public ResponseEntity<UserOutputModel> loginInfo(@PathVariable("emailId") String emailId,String password) throws InvalidLoginException {
		logger.info("addUser() method started");
		UserOutputModel userOutputModel = userService.loginInfo(emailId, password);
		logger.info("loginInfo ={}",userOutputModel);
		logger.info("loginInfo() method ended");
		return new ResponseEntity<UserOutputModel>(userOutputModel,HttpStatus.OK);
	}
}
