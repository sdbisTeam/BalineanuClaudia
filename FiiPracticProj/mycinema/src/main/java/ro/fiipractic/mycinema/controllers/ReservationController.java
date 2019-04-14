package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.dtos.ReservationDto;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.MovieInstanceService;
import ro.fiipractic.mycinema.services.PersonService;
import ro.fiipractic.mycinema.services.ReservationService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private MovieInstanceService movieInstanceService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Reservation> getAllReservations(){
        return reservationService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Reservation getReservationById(@PathVariable("id") Long id) throws NotFoundException {
        return reservationService.getReservationById(id);
    }

    @GetMapping(value = "/filter/{personId}/{movieInstanceId}")
    public List<Reservation> getAllReservationsByIds(@PathVariable Long personId, @PathVariable Long movieInstanceId) {
        return reservationService.getAllReservationsByIds(personId, movieInstanceId);
    }

    @PostMapping
    public ResponseEntity<Reservation> saveReservation(@RequestBody ReservationDto reservationDto) throws URISyntaxException{
        Reservation reservation = reservationService.saveReservation(modelMapper.map(reservationDto, Reservation.class));

        return ResponseEntity.created(new URI("/api/reservations/" + reservation.getId())).body(reservation);
    }
}
