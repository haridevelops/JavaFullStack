package com.haridevelops.moviecruiserapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.haridevelops.moviecruiserapp.exception.UserAlreadyExistsException;
import com.haridevelops.moviecruiserapp.exception.UserNotFoundException;
import com.haridevelops.moviecruiserapp.repo.UserRepository;
import com.haridevelops.moviecruiserapp.domain.UserBean;
import com.haridevelops.moviecruiserapp.services.UserServiceImpl;

public class UserService {

	@Mock
	private UserRepository userRepository;
	
	private UserBean user;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	Optional<UserBean> options;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new UserBean("haridevelops", "harisudhan", "manivannan", "password", new Date());
		options = Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccesS() throws UserNotFoundException, UserAlreadyExistsException {
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("cannot register user", true, flag);
		verify(userRepository, times(1)).save(user);
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserNotFoundException, UserAlreadyExistsException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);	
	}
	
	@Test
	public void testValidateSuccess() throws UserNotFoundException, UserAlreadyExistsException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		UserBean userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("haridevelops", userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException, UserAlreadyExistsException {
		when(userRepository.findById("hari")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}
