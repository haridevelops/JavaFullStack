package com.haridevelops.moviecruiserapp.services;

import java.util.Map;

import com.haridevelops.moviecruiserapp.repo.model.UserBean;

public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(UserBean user);
}
