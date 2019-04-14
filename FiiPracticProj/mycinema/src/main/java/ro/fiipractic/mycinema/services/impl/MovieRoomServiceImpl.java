package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.MovieRoom;
import ro.fiipractic.mycinema.repositories.MovieRoomRepository;
import ro.fiipractic.mycinema.services.CinemaService;
import ro.fiipractic.mycinema.services.MovieRoomService;

import java.util.List;

@Service
public class MovieRoomServiceImpl implements MovieRoomService {

    @Autowired
    private MovieRoomRepository movieRoomRepository;

    @Override
    public List<MovieRoom> getAll() {
        return movieRoomRepository.findAll();
    }

    @Override
    public MovieRoom saveMovieRoom(MovieRoom movieRoom) {
        return movieRoomRepository.save(movieRoom);
    }

    @Override
    public List<MovieRoom> getAllMovieRoomsByCinemaId(Long cinemaId) {
        return movieRoomRepository.getMovieRoomsByCinema_Id(cinemaId);
    }
}
