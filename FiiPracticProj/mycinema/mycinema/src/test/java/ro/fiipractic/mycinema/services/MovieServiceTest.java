package ro.fiipractic.mycinema.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieRepository;
import ro.fiipractic.mycinema.services.impl.MovieServiceImpl;

import java.util.Optional;

import static org.junit.Assert.fail;
import static java.util.Optional.empty;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        movie = buildMovie();
    }

    @Test
    public void shouldReturnMovieById() throws NotFoundException {
        //arrange
        Mockito.when(movieRepository.findById(1L)).thenReturn(Optional.ofNullable(movie));

        //act
        Movie movieById = movieService.getMovieById(1L);

        //assert
        Assertions.assertThat(movieById).isNotNull();
        Assertions.assertThat(movieById).isEqualToComparingFieldByFieldRecursively(movie);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenMovieById() throws NotFoundException {
        //arrange
        Mockito.when(movieRepository.findById(2L)).thenReturn(empty());

        //act
        Movie movieById = movieService.getMovieById(2L);

        //assert
        fail();
    }

    private Movie buildMovie(){
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("MovieTitle1");
        movie.setDescription("Super cool movie! :D");
        movie.setDuration_minutes(120);
        return movie;
    }

}
