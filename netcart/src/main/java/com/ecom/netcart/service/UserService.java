package com.ecom.netcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.netcart.entity.User;
import com.ecom.netcart.exception.InvalidLoginException;
import com.ecom.netcart.exception.InvalidRegistrationException;
import com.ecom.netcart.model.UserInputModel;
import com.ecom.netcart.model.UserOutputModel;
import com.ecom.netcart.repository.UserDao;
import com.ecom.netcart.util.MapUserRow;

@Service
public interface UserService {
	

		public UserOutputModel addUser(UserInputModel userinputmodel)throws InvalidRegistrationException;
		public UserOutputModel loginInfo(String emailId,String password)throws InvalidLoginException;
			
}		
