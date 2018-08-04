package com.haridevelops.moviecruiserapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haridevelops.moviecruiserapp.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	//@Query
	List<Movie> findByuserId(String userId); 
}
