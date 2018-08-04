package com.haridevelops.moviecruiserapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.haridevelops.moviecruiserapp.exception.UserAlreadyExistsException;
import com.haridevelops.moviecruiserapp.exception.UserNotFoundException;
import com.haridevelops.moviecruiserapp.repo.model.UserBean;
import com.haridevelops.moviecruiserapp.services.SecurityTokenGenerator;
import com.haridevelops.moviecruiserapp.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
@EnableWebMvc
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityTokenGenerator tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserBean bean) throws UserAlreadyExistsException{
		try {
			userService.saveUser(bean);
			return new ResponseEntity<String>("USer registered Successfully", HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<String>("message : "+ e, HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserBean userDetail) throws UserNotFoundException {
		try {
			String userId = userDetail.getUserId();
			String password = userDetail.getPassword();
			
			if(userId == null || password == null) {
				throw new Exception ("Username or password cannot be empty!!");
			}
			
			UserBean  user = userService.findByUserIdAndPassword(userId, password);
			if(user == null) {
				throw new Exception("User id does not exists");
			}
			
			if(!password.equals(user.getPassword())) {
				throw new Exception("Invalid Credentials. Please try with correct username and password");
			}
			
			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String,String>>(map, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("message : " + e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
}
