package com.haridevelops.moviecruiserapp.services;

import java.util.Map;

import com.haridevelops.moviecruiserapp.domain.UserBean;

public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(UserBean user);
}
