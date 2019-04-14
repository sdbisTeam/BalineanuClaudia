package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.fiipractic.mycinema.entities.MovieInstance;

import java.util.List;

public interface MovieInstanceRepository extends JpaRepository<MovieInstance, Long> {

    @Query("SELECT mi FROM MovieInstance mi JOIN mi.cinema JOIN mi.movie JOIN mi.movieRoom")
    List<MovieInstance> getMovieInstancesByAll_Ids(Long cinemaId, Long movieId, Long movieRoomId);
}
