package com.haridevelops.moviecruiserapp.service;

import java.util.List;

import com.haridevelops.moviecruiserapp.domain.Movie;
import com.haridevelops.moviecruiserapp.exception.MovieAlreadyExistException;
import com.haridevelops.moviecruiserapp.exception.MovieNotFoundException;

public interface MovieService {
	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistException;
	Movie updateMovie(int id, Movie movie) throws MovieNotFoundException;
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	Movie getMovieById(int id) throws MovieNotFoundException;
	List<Movie> getMyMovies(String userId);
}
