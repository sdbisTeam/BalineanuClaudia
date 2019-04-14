package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface MovieInstanceService {

    List<MovieInstance> getAll();

    MovieInstance getMovieInstanceById(Long id) throws NotFoundException;

    MovieInstance saveMovieInstance(MovieInstance movieInstance);

    List<MovieInstance> getAllMovieInstancesByAllIds(Long cinemaId, Long movieId, Long movieRoomId);
}
