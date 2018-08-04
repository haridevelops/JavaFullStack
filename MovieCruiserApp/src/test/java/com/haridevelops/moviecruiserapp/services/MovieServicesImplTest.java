package com.haridevelops.moviecruiserapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Verify;
import com.haridevelops.moviecruiserapp.domain.Movie;
import com.haridevelops.moviecruiserapp.exception.MovieAlreadyExistException;
import com.haridevelops.moviecruiserapp.exception.MovieNotFoundException;
import com.haridevelops.moviecruiserapp.repo.MovieRepository;
import com.haridevelops.moviecruiserapp.service.MovieServiceImpl;

public class MovieServicesImplTest {

	@Mock
	private transient MovieRepository movieRepo;
	
	@InjectMocks
	private transient MovieServiceImpl impl;

	private transient Movie movie;
	
	Optional<Movie> options;
	
	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1, "100", "harisudhan", "Hari's AutoBioGraphy", "10-APR-2016", "Nice Movie", "www.haridevelops.com", "hello");
		options = Optional.of(movie);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(movie);
		assertNotNull("JPA repo creation Failure..", movieRepo);
	}
	
	
	@Test
	public void testSaveSuccess() throws MovieAlreadyExistException {
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag = impl.saveMovie(movie);
		assertEquals("Saving movie Failure...", true, flag);
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test(expected = MovieAlreadyExistException.class)
	public void testSaveFailure() throws MovieAlreadyExistException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag = impl.saveMovie(movie);
	}
	
	@Test
	public void testUpdate() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setMovieComments("hello");
		Movie upMovie = impl.updateMovie(1, movie);
		assertEquals("hello", upMovie.getMovieComments());
		verify(movieRepo, times(1)).save(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testDeleteByMovieId() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		boolean flag = impl.deleteMovieById(1);
		assertEquals("Delete Movie Failure", true, flag);
		verify(movieRepo, times(1)).delete(movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		Movie movie1 = impl.getMovieById(1);
		assertEquals("fetching movie by id failed", movie1, movie);
		verify(movieRepo, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetAllMovies() throws MovieNotFoundException {
		List<Movie> movieList = new ArrayList<>();
		movieList.add(movie);
		when(movieRepo.findByuserId("harisudhan")).thenReturn(movieList);
		List<Movie> movies1 = impl.getMyMovies("harisudhan");
		assertEquals(movieList, movies1);
		verify(movieRepo, times(1)).findByuserId("harisudhan");
	}
	
}
