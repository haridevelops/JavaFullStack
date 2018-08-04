package com.haridevelops.moviecruiserapp.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haridevelops.moviecruiserapp.controller.MovieController;
import com.haridevelops.moviecruiserapp.domain.Movie;
import com.haridevelops.moviecruiserapp.exception.MovieAlreadyExistException;
import com.haridevelops.moviecruiserapp.service.MovieService;

import net.minidev.json.JSONObject;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	private MockMvc mvc;
	
	@MockBean
	private MovieService service;

	@InjectMocks
	private MovieController movieController;
	
	private Movie movie;
	
	static List<Movie> movieList;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(movieController).build();

		movieList = new ArrayList<>();
		movie = new Movie(1, "100", "harisudhan", "Hari's AutoBioGraphy","10-APR-2016", "Nice Movie", "www.haridevelops.com", "hello");
		movieList.add(movie);
		movie = new Movie(2, "200", "haridevelops", "Hari's BioGraphy", "Great Movie", "10-APR-2016", "www.haridevelops1.com", "hello1");
		movieList.add(movie);
	}
	
	
//	@Test
//	public void testSaveMovieSuccess() throws Exception {
//		String token = "akjsfoiuioaksjghsjhn.auishfpahfpaihsfhalkhsgfakhsfkahklsfh;akgsfoahsfkahisfha.aslfhasyfiahfkjatkljrhalksglk";
//		
//		when(service.saveMovie(movie)).thenReturn(true);
//		mvc.perform(post("/api/v1/movieservice/movie").header("authorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON)
//				.content(jsonToString(movie))).andExpect(status().isCreated());
//		verify(service, times(1)).saveMovie(Mockito.any(Movie.class));
////		verifyNoMoreInteractions(service);
	// }
	
	// @Test
	// public void testUpdateMovieSuccess() throws Exception {
	// 	movie.setMovieComments("Wow Movie");
	// 	when(service.updateMovie(movie.getId(), movie)).thenReturn(movieList.get(0));
	// 	mvc.perform(put("/api/movie/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isOk());
	// 	verify(service, times(1)).updateMovie(Mockito.any(Movie.class));
	// 	verifyNoMoreInteractions(service);
	// }
	
	@Test
	public void testDeleteMovieById() throws Exception {
		when(service.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/movieservice/movie/{id}",1)).andExpect(status().isOk());
		verify(service, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(service);
	}
//	
//	@Test
//	public void testFetchMovieById() throws Exception {
//		when(service.getMovieById(1)).thenReturn(movieList.get(0));
//		mvc.perform(get("/api/movie/{id}", 1)).andExpect(status().isOk());
//		verify(service, times(1)).getMovieById(1);
//		verifyNoMoreInteractions(service);
//	}
	
	// @Test
	// public void testGetMyMovies() throws Exception {
	// 	String token = "akjsfoiuioaksjghsjhn.auishfpahfpaihsfhalkhsgfakhsfkahklsfh;akgsfoahsfkahisfha.aslfhasyfiahfkjatkljrhalksglk";
		
		
	// 	when(service.getMyMovies("100")).thenReturn(movieList);
	// 	mvc.perform(get("/api/v1/movieservice/movies").header("authorization", "Bearer "+token)
	// 			.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
	// 	verify(service, times(1)).getMyMovies("100");
	// 	verifyNoMoreInteractions(service);
	// }
	
	
	
	private static String jsonToString(Object movie) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(movie);
		
	}
}
