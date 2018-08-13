import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

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
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repo;
    
    private Movie Omovie;

    @Before
    public void setUp() throws Exception {
        Omovie = new Movie(1, "100", "hari", "ddlj", "1995-05-05", "" ,"", "hari");
    }

    @Test
    public void testSaveMovie() throws Exception{
        repo.save(Omovie);
        Movie movie = repo.getOne(1);
        assertEquals(1, movie.getId());
    }

    @Test
    public void testUpdateMovie() throws Exception {
        repo.save(Omovie);
        Movie movie = repo.getOne(1);
        assertEquals("100", movie.getMovieId());
        movie.setMovieComments("super movie");
        repo.save(movie);
        Movie tempMovie = repo.getOne(1);
        assertEquals(tempMovie.getMovieComments(), "super movie");
    }

    @Test
    public void testDeleteMovie() throws Exception{
        repo.save(Omovie);
        Movie movie = repo.getOne(1);
        assertEquals("100", movie.getMovieId());
        repo.delete(movie);
        assertEquals(Optional.empty(), repo.findById(1));
    }

    @Test
    public void testGetAllMovies() throws Exception {
        repo.save(Omovie);
        repo.save(new Movie(2, "100", "hari", "ddlj", "1995-05-05", "" ,"", ""));
        List<Movie> movies = repo.findAll();
        assertEquals(movies.get(0).getMovieId(), "100");
    }

    @Test() 
    public void testGetMyMovies() {
        repo.save(Omovie);
        repo.save(Omovie);
        repo.save(Omovie);
        repo.save(Omovie);
        repo.save(Omovie);
        repo.save(new Movie(2, "100", "hari", "ddlj", "1995-05-05", "" ,"", "harisudhan"));       
        List<Movie> movies = repo.findByuserId("harisudhan");
        assertEquals("hari", movies.get(0).getName());
    }
}