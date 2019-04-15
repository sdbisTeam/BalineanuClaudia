package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieInstanceRepository;
import ro.fiipractic.mycinema.services.MovieInstanceService;

import java.util.List;

@Service
public class MovieInstanceServiceImpl implements MovieInstanceService {

    @Autowired
    private MovieInstanceRepository movieInstanceRepository;

    @Override
    public List<MovieInstance> getAll() {
        return movieInstanceRepository.findAll();
    }

    @Override
    public MovieInstance getMovieInstanceById(Long id) throws NotFoundException {
        return movieInstanceRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("MovieInstance with id=%s was not found.", id)));
    }

    @Override
    public MovieInstance saveMovieInstance(MovieInstance movieInstance) {
        return movieInstanceRepository.save(movieInstance);
    }

    @Override
    public List<MovieInstance> getAllMovieInstancesByAllIds(Long cinemaId, Long movieId, Long movieRoomId) {
        return movieInstanceRepository.getMovieInstancesByAll_Ids(cinemaId,movieId, movieRoomId);
    }
}
