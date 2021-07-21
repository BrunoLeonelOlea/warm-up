package com.alkemy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.entity.User;
import com.alkemy.repository.UserRepositoy;

@Service
public class UserService {
	
	@Autowired
	private UserRepositoy userRepositoy;
	
	public User createUser(User user) throws Exception{
		try {
			return userRepositoy.save(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
