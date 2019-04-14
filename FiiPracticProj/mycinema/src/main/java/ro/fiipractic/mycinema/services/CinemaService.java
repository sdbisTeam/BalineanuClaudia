package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface CinemaService {

    List<Cinema> getAll();

    Cinema getCinemaById(Long id) throws NotFoundException;

    Cinema saveCinema(Cinema cinema);

    List<Cinema> getCinemasByMovieRoomsCapacity(Integer capacity);
}
