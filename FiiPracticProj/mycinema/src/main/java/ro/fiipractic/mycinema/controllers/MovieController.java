package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entities.Movie;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.MovieService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Movie getMovies(@PathVariable("id") Long id) throws NotFoundException {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movieToSave) throws URISyntaxException {
        Movie movie = movieService.saveMovie(modelMapper.map(movieToSave, Movie.class));
        return ResponseEntity.created(new URI("/api/movies/" + movie.getId())).body(movie);
    }

    @PutMapping(value = "/{id}")
    public Movie updateMovie(@PathVariable("id") Long id, @RequestBody Movie movieToUpdate) throws NotFoundException, BadRequestException {
        if(!id.equals(movieToUpdate.getId())){
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + movieToUpdate.getId() + " from RequestBody");
        }
        Movie movieDb = movieService.getMovieById(id);

        modelMapper.map(movieToUpdate, movieDb);

        return movieService.updateMovie(movieDb);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMovie(@PathVariable Long id) throws NotFoundException {
        Movie movieDb = movieService.getMovieById(id);

        movieService.deleteMovie(movieDb);
    }
}
