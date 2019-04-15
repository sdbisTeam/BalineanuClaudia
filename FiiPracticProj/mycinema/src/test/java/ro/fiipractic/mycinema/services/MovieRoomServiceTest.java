package ro.fiipractic.mycinema.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieRoomRepository;
import ro.fiipractic.mycinema.services.impl.MovieRoomServiceImpl;

import java.util.List;
import java.util.Optional;

public class MovieRoomServiceTest {

    @Mock
    private MovieRoomRepository movieRoomRepository;

    @InjectMocks
    private MovieRoomServiceImpl movieRoomService;

    private MovieRoom movieRoom;
    private Cinema cinema;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        movieRoom = buildMovieRoom();
        cinema = buildCinema();
    }

    @Test
    public void shouldReturnMovieRoomByCinemaId() throws NotFoundException {
        //arrange
        Mockito.when(movieRoomRepository.findById(1L)).thenReturn(Optional.ofNullable(movieRoom));

        //act
        List<MovieRoom> movieRoomById = movieRoomService.getAllMovieRoomsByCinemaId(1L);

        //assert
        Assertions.assertThat(movieRoomById).isNotNull();
        Assertions.assertThat(movieRoomById).containsAnyOf(movieRoom);
    }

    private MovieRoom buildMovieRoom(){
        MovieRoom movieRoom = new MovieRoom();
        movieRoom.setId(1L);
        movieRoom.setName("movieRoomName");
        movieRoom.setCapacity(50);
        movieRoom.setCinema(cinema);
        return movieRoom;
    }

    private Cinema buildCinema() {
        Cinema cinema = new Cinema();
        cinema.setId(1L);
        cinema.setAddress("address");
        cinema.setName("cinemaName");
        return cinema;
    }
}
