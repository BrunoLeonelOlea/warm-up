package com.alkemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.entity.User;
import com.alkemy.entity.UserResponse;
import com.alkemy.service.MyUserDetailsService;
import com.alkemy.service.UserService;
import com.alkemy.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/sign_up")
	public ResponseEntity<?> register(@RequestBody User user){
		try {
			return ResponseEntity.status(201).body(userService.createUser(user));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> autenticacion(@RequestBody User user) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (BadCredentialsException bce) {
			throw new Exception("Email or Password incorrects", bce);
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getEmail());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new UserResponse(jwt));
	}

}
