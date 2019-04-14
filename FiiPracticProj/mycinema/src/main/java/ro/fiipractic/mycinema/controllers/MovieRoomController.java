package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.MovieRoomDto;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.services.CinemaService;
import ro.fiipractic.mycinema.services.MovieRoomService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movie-rooms")
public class MovieRoomController {

    @Autowired
    private MovieRoomService movieRoomService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<MovieRoom> getAllMovieRooms(){
        return movieRoomService.getAll();
    }

    @GetMapping(value = "/filter/{cinemaId}")
    public List<MovieRoom> getAllMovieRoomsByCinemaId(@PathVariable Long cinemaId){
        return movieRoomService.getAllMovieRoomsByCinemaId(cinemaId);
    }

    @PostMapping
    public ResponseEntity<MovieRoom> saveMovieRoom(@RequestBody MovieRoomDto movieRoomDto) throws URISyntaxException {
        MovieRoom movieRoom = movieRoomService.saveMovieRoom(modelMapper.map(movieRoomDto, MovieRoom.class));

        return ResponseEntity.created(new URI("/api/movie-rooms/" + movieRoom.getId())).body(movieRoom);
    }

}
