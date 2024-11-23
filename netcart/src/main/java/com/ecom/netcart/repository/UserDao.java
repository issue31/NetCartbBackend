package com.ecom.netcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.netcart.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.email =:emailId")
	User getEmail(String emailId);

}
