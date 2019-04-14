package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.MovieRepository;
import ro.fiipractic.mycinema.services.MovieService;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) throws NotFoundException {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Movie with id=%s was not found.", id)));
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

}
