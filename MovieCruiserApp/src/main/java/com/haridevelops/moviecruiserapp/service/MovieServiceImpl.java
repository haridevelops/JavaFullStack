package com.haridevelops.moviecruiserapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haridevelops.moviecruiserapp.domain.Movie;
import com.haridevelops.moviecruiserapp.exception.MovieAlreadyExistException;
import com.haridevelops.moviecruiserapp.exception.MovieNotFoundException;
import com.haridevelops.moviecruiserapp.repo.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	private final transient MovieRepository movieRepo;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistException {
		Optional<Movie> object = movieRepo.findById(movie.getId());
		if(object.isPresent())
			throw new MovieAlreadyExistException("Movie already exists and hence cannot be saved");
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(int id, Movie upMovie) throws MovieNotFoundException {
		Movie movie = movieRepo.findById(id).orElse(null);
		
		if(movie == null) throw new MovieNotFoundException("Movie not found.. Update Failure");
			
		movie.setMovieComments(upMovie.getMovieComments());
		movieRepo.save(movie);
		return movie;
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		Movie movie = movieRepo.findById(id).orElse(null);
		if(movie == null) throw new MovieNotFoundException("Movie not found.. Delete Failure");
		
		movieRepo.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		Movie movie = movieRepo.findById(id).get();
		if(movie == null) throw new MovieNotFoundException("Movie not found..");
		return movie;
	}

	@Override
	public List<Movie> getMyMovies(String userId) {
		return movieRepo.findByuserId(userId);
	}

}
