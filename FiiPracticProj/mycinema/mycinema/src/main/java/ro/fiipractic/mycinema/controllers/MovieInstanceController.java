package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.MovieInstanceDto;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.CinemaService;
import ro.fiipractic.mycinema.services.MovieInstanceService;
import ro.fiipractic.mycinema.services.MovieRoomService;
import ro.fiipractic.mycinema.services.MovieService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movie-instances")
public class MovieInstanceController {

    @Autowired
    private MovieInstanceService movieInstanceService;

    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRoomService movieRoomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<MovieInstance> getAllMovieInstances() {
        return movieInstanceService.getAll();
    }

    @GetMapping(value = "/{id}")
    public MovieInstance getMovieInstanceById(@PathVariable("id") Long id) throws NotFoundException {
        return movieInstanceService.getMovieInstanceById(id);
    }

    @GetMapping(value = "/filters/{cinemaId}/{movieId}/{movieRoomId}")
    public List<MovieInstance> getAllMovieInstancesByAllIds(@PathVariable Long cinemaId, @PathVariable Long movieId, @PathVariable Long movieRoomId){
        return movieInstanceService.getAllMovieInstancesByAllIds(cinemaId, movieId, movieRoomId);
    }

    @PostMapping
    public ResponseEntity<MovieInstance> saveMovieInstance(@RequestBody MovieInstanceDto movieInstanceDto)  throws URISyntaxException {
        MovieInstance movieInstance = movieInstanceService.saveMovieInstance(modelMapper.map(movieInstanceDto, MovieInstance.class));

        return ResponseEntity.created(new URI("/api/movie-instances/" + movieInstance.getId())).body(movieInstance);
    }

}
