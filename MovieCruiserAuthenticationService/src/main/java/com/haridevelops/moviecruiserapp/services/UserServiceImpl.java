package com.haridevelops.moviecruiserapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haridevelops.moviecruiserapp.exception.UserAlreadyExistsException;
import com.haridevelops.moviecruiserapp.exception.UserNotFoundException;
import com.haridevelops.moviecruiserapp.repo.UserRepository;
import com.haridevelops.moviecruiserapp.repo.model.UserBean;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepo;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public boolean saveUser(UserBean user) throws UserAlreadyExistsException, UserNotFoundException {
		Optional<UserBean> op = userRepo.findById(user.getUserId());
		if(op.isPresent()) {
			throw new UserAlreadyExistsException("User id already exists. Please try with different user id.");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public UserBean findByUserIdAndPassword(String userId, String password)
			throws UserAlreadyExistsException, UserNotFoundException {
		UserBean user = userRepo.findByUserIdAndPassword(userId, password);
		
		if(user == null) {
			throw new UserNotFoundException("Please register to login");
		}
		
		return user;
	}
	
	
}
