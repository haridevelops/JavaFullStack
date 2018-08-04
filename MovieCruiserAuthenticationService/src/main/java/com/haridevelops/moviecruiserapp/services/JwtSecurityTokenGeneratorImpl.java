package com.haridevelops.moviecruiserapp.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.haridevelops.moviecruiserapp.repo.model.UserBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generateToken(UserBean user) {
		HashMap<String, String> map = new HashMap<>();
		map.put("token",
				Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		map.put("message", "User Log in Successful");
		
		return map;
	}

}
