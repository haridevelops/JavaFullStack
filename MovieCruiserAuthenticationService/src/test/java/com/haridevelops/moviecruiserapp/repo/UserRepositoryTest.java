package com.haridevelops.moviecruiserapp.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.haridevelops.moviecruiserapp.repo.UserRepository;
import com.haridevelops.moviecruiserapp.repo.model.UserBean;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	private UserBean user;
	
	@Before
	public void setUp() throws Exception {
		user = new UserBean("haridevelops", "harisudhan", "manivannan", "password", new Date());
	}
	
	
	@Test
	public void testRegisterUserSuccess() {
		userRepository.save(user);
		Optional<UserBean> op = userRepository.findById(user.getUserId());
		assertThat(op.equals(user));
	}
	
	
}
