package com.haridevelops.moviecruiserapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.haridevelops.moviecruiserapp.filters.JwtFilter;

@SpringBootApplication
public class MovieCruiserAppApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean bean = new FilterRegistrationBean<>();
		bean.setFilter(new JwtFilter());
		bean.addUrlPatterns("/api/*");
		
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserAppApplication.class, args);
	}
}
