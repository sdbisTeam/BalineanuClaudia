package ro.fiipractic.mycinema.controllers;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.impl.MovieServiceImpl;

import java.net.URISyntaxException;


public class MovieControllerTest {

    @Mock
    private MovieServiceImpl movieService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieController controller;

    private Movie movie;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movie = buildMovie();

    }

    @Test
    public void shouldReturnMovieById() throws NotFoundException {
        //arrange
        Mockito.when(movieService.getMovieById(1L)).thenReturn(movie);

        //act
        Movie movieById = movieService.getMovieById(1L);

        //assert
        Assertions.assertThat(movieById).isNotNull();
        Assertions.assertThat(movieById).isEqualToComparingFieldByFieldRecursively(movie);
    }

    @Test
    public void shouldReturnResponseEntityForSave() throws URISyntaxException {
        //arrange
        Mockito.when(movieService.saveMovie(movie)).thenReturn(movie);
        Mockito.when(modelMapper.map(movie, Movie.class)).thenReturn(movie);

        //act
        ResponseEntity<Movie> movieResponseEntity = controller.saveMovie(movie);

        //assert
        Assertions.assertThat(movieResponseEntity).isNotNull();
        Assertions.assertThat(movieResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(movieResponseEntity.getBody()).isEqualToComparingFieldByFieldRecursively(movie);
        Assertions.assertThat(movieResponseEntity.getHeaders().getLocation().toString()).isEqualTo("/api/movies/1");

    }

    @After
    public void tearDown() {

        movie = null;
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
