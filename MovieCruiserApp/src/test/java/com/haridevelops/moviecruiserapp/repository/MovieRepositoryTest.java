package com.haridevelops.moviecruiserapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.haridevelops.moviecruiserapp.domain.Movie;
import com.haridevelops.moviecruiserapp.repo.MovieRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {
	
	@Autowired
    private transient MovieRepository repo;
	public void setRepo(final MovieRepository repo) {
		this.repo = repo;
	}
    
	

    @Test
    public void testSaveMovie() throws Exception{
        repo.save(new Movie(1, "100", "hari", "ddlj", "1995-05-05", "lovely" ,"source", "hari"));
        Movie movie = repo.getOne(1);
        assertThat(movie.getId()).isEqualTo(1);
        //repo.delete(movie);
    }

//    @Test
//    public void testUpdateMovie() throws Exception {
//        repo.save(new Movie(655, "200", "harisudhan manivannan", "dhadak", "1995-05-05","lovely" ,"source", "anna"));
//        Movie movie = repo.getOne(655);
//        assertEquals("harisudhan manivannan", movie.getName());
//        movie.setMovieComments("super movie");
//        repo.save(movie);
//        Movie tempMovie = repo.getOne(655);
//        assertEquals(tempMovie.getMovieComments(), "super movie");
//        repo.delete(tempMovie);
//    }
//
//
//	@Test
//    public void testDeleteMovie() throws Exception{
//        repo.save(new Movie(666, "200", "harisudhan manivannan", "dhadak", "1995-05-05","lovely" ,"source" , "anna"));
//        Movie movie = repo.getOne(666);
//        assertEquals("200", movie.getMovieId());
//        repo.delete(movie);
//        assertEquals(Optional.empty(), repo.findById(666));
//    }

    @Test() 
    public void testGetMyMovies() {
        repo.save(new Movie(664, "200", "harisudhan manivannan", "dhadak", "1995-05-05","lovely" ,"source", "anna"));
        repo.save(new Movie(665, "200", "harisudhan manivannan", "dhadak", "1995-05-05", "lovely" ,"source", "anna"));
        repo.save(new Movie(666, "200", "harisudhan manivannan", "dhadak", "1995-05-05","lovely" ,"source" , "anna"));
        repo.save(new Movie(667, "200", "harisudhan manivannan", "dhadak", "1995-05-05", "lovely" ,"source", "anna"));
        repo.save(new Movie(668, "200", "harisudhan manivannan", "dhadak", "1995-05-05", "lovely" ,"source", "anna"));
        repo.save(new Movie(2, "100", "hari", "ddlj", "1995-05-05","lovely" ,"source", "harisudhan"));       
        List<Movie> movies = repo.findByuserId("harisudhan");
        assertEquals("hari", movies.get(0).getName());
    }
}