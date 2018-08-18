package com.haridevelops.moviecruiserapp.services;

import com.haridevelops.moviecruiserapp.exception.UserAlreadyExistsException;
import com.haridevelops.moviecruiserapp.exception.UserNotFoundException;
import com.haridevelops.moviecruiserapp.domain.UserBean;

public interface UserService {

	//to register the user
	boolean saveUser(UserBean user) throws UserAlreadyExistsException, UserNotFoundException;
	
	//to check before signin
	public UserBean findByUserIdAndPassword(String userId, String password) throws UserAlreadyExistsException, UserNotFoundException;
}
