package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.CinemaDto;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.services.CinemaService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Cinema> getAllCinemas(){
        return cinemaService.getAll();
    }

    @GetMapping(value = "/filter")
    public List<Cinema> getCinemasByMovieRoomsCapacity(@RequestParam("capacity") Integer capacity){
        return cinemaService.getCinemasByMovieRoomsCapacity(capacity);
    }

    @PostMapping
    public ResponseEntity<Cinema> saveCinema(@RequestBody CinemaDto cinemaDto) throws URISyntaxException {

        Cinema map = modelMapper.map(cinemaDto, Cinema.class);
        Cinema cinema = cinemaService.saveCinema(map);

        return ResponseEntity.created(new URI("/api/cinemas/" + cinema.getId())).body(cinema);
    }
}
