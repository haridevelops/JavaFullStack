package com.haridevelops.moviecruiserapp.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haridevelops.moviecruiserapp.domain.Movie;
import com.haridevelops.moviecruiserapp.exception.MovieAlreadyExistException;
import com.haridevelops.moviecruiserapp.exception.MovieNotFoundException;
import com.haridevelops.moviecruiserapp.service.MovieService;

import io.jsonwebtoken.Jwts;

@CrossOrigin
@RestController
@RequestMapping(path ="/api/v1/movieservice")
public class MovieController {

	@Autowired
	private MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/movie")
	public ResponseEntity<?> saveNewMovie(@RequestBody Movie movie, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseEntity<?> resEntity;
		System.out.println("saving movie");
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser()
							.setSigningKey("secretkey")
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
		System.out.println("user id : " + userId);
		try {
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			resEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch(MovieAlreadyExistException exp) {
			resEntity = new ResponseEntity<String>("[message= "+exp.getMessage()+" ]", HttpStatus.CONFLICT);
		}
		return resEntity;
	}

	@PutMapping(path="/movie/{id}")
	public ResponseEntity<?> updateNewMovie(@PathVariable("id") Integer id, @RequestBody Movie movie) {
		ResponseEntity<?> resEntity;
		Movie fetchedMovie = null;
		try {
			fetchedMovie = movieService.updateMovie(id, movie);
			resEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		} catch (MovieNotFoundException exp) {
			resEntity = new ResponseEntity<String>("[message= " + exp.getMessage()+" ]", HttpStatus.CONFLICT);
		}
		return resEntity;
	}


	@DeleteMapping(value="/movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") int id) {
		ResponseEntity<?> resEntity;
		try {
			movieService.deleteMovieById(id);
		} catch (MovieNotFoundException exp) {
			resEntity = new ResponseEntity<String>("[message= " + exp.getMessage()+" ]", HttpStatus.NOT_FOUND);
		}
		resEntity = new ResponseEntity<String>("Movie Deletion Success..", HttpStatus.OK);
		return resEntity;
	}

	@GetMapping(path="/movie/{id}")
	public ResponseEntity<?> getMovie(@PathVariable("id") int id) {
		Movie moviee = null;
		ResponseEntity<?> resEntity;
		try {
			moviee = movieService.getMovieById(id);
		} catch (MovieNotFoundException exp) {
			resEntity = new ResponseEntity<String>("[message= " + exp.getMessage()+" ]", HttpStatus.NOT_FOUND);
		}
		resEntity = new ResponseEntity<Movie>(moviee, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping(path = "/movies")
	public ResponseEntity<?> getMyMovies(final ServletRequest req, final ServletResponse res) {

		final HttpServletRequest request = (HttpServletRequest) req;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser()
							.setSigningKey("secretkey")
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
		List<Movie> movie = movieService.getMyMovies(userId);
		return new ResponseEntity<List<Movie>>(movie, HttpStatus.OK);
	}
}
