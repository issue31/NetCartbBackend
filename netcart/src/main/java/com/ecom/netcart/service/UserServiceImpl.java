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
import com.ecom.netcart.validations.UserValidation;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired 
	UserDao userdao;
	@Autowired
	MapUserRow mapUserRow;
	@Autowired
	UserValidation userValidation;

		public UserOutputModel addUser(UserInputModel userinputmodel) throws InvalidRegistrationException{
			if (!userValidation.checkEmail(userinputmodel.getEmail())) {
	            throw new InvalidRegistrationException("Email is not valid");
	        }
	        if (!userValidation.validatePassword(userinputmodel.getPassword())) {
	            throw new InvalidRegistrationException("Password is not valid");
	        }   
	        if (!userValidation.checkName(userinputmodel.getUsername())) {
	            throw new InvalidRegistrationException("Username is not valid");
	        }
	       
		    User user = new User();
		    user.setUsername(userinputmodel.getUsername());
		    user.setAddress(userinputmodel.getAddress());
		    user.setEmail(userinputmodel.getEmail());
		    user.setPassword(userinputmodel.getPassword());
            userdao.save(user);

		    UserOutputModel userOut = mapUserRow.mapToUserOutput(user);
		    return userOut;
		}
		public UserOutputModel loginInfo(String emailId,String password) throws InvalidLoginException {
			User user = userdao.getEmail(emailId);
			if(user.getPassword().equals(password))
			{
				UserOutputModel userOut = mapUserRow.mapToUserOutput(user);
			    return userOut;
			}
			else {
				throw new InvalidLoginException("The credentials are incorrect");
			}
		}
}
