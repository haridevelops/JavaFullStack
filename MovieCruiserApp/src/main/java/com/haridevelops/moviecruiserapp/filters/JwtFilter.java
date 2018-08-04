package com.haridevelops.moviecruiserapp.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		final String authHeader = req.getHeader("authorization");
		
		if("OPTIONS".equals(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			if(authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or Invalid Authorization Header");
			}
			
			final String token = authHeader.substring(7);
			final Claims claims = Jwts.parser()
									  .setSigningKey("secretkey")
									  .parseClaimsJws(token)
									  .getBody();
			
			req.setAttribute("claims", claims);
			chain.doFilter(req, res);
		}
		
	}

}
