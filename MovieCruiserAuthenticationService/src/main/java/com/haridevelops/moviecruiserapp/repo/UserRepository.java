package com.haridevelops.moviecruiserapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haridevelops.moviecruiserapp.domain.UserBean;

public interface UserRepository extends JpaRepository<UserBean, String> {

	UserBean findByUserIdAndPassword(String userId, String Password);
}
