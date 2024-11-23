package com.ecom.netcart.util;



import org.springframework.stereotype.Component;

import com.ecom.netcart.entity.User;
import com.ecom.netcart.model.UserOutputModel;
@Component
public class MapUserRow {//To mapUseroutputmodel to user,to not epeatedly writethe coder
  public UserOutputModel mapToUserOutput(User user) {
	  UserOutputModel model = new UserOutputModel();
	  model.setAddress(user.getAddress());
	  model.setUsername(user.getUsername());
	  model.setEmail(user.getEmail());
	  model.setUserId(user.getUserId());
	  return model;
  }
}
