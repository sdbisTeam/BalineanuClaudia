package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.Cinema;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.repositories.CinemaRepository;
import ro.fiipractic.mycinema.services.CinemaService;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema getCinemaById(Long id) throws NotFoundException {
        return cinemaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cinema with id=%s was not found.", id)));
    }

    @Override
    public Cinema saveCinema(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public List<Cinema> getCinemasByMovieRoomsCapacity(Integer capacity) {
        return cinemaRepository.getCinemasByMovieRoomsCapacity(capacity);
    }
}
