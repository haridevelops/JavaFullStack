package com.haridevelops.moviecruiserapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name="movieId")
	private String movieId;
	
	@Column(name="name")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Movie(int id, String movieId, String name, String title, String release_date, String movieComments,
			String poster_path, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.name = name;
		this.title = title;
		this.release_date = release_date;
		this.movieComments = movieComments;
		this.poster_path = poster_path;
		this.userId = userId;
	}
	@Column(name="title")
	private String title;
	@Column(name="release_date")
	private String release_date;
	@Column(name="movieComments")
	private String movieComments;
	@Column(name="poster_path")
	private String poster_path;
	@Column(name="userId")
	private String userId;
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Movie() {
		
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getMovieComments() {
		return movieComments;
	}
	public void setMovieComments(String movieComments) {
		this.movieComments = movieComments;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
		
}
