package com.alkemy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.entity.User;
import com.alkemy.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) throws Exception{
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
