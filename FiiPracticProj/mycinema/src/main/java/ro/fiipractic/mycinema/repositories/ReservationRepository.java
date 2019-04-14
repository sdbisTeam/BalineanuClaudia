package ro.fiipractic.mycinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.fiipractic.mycinema.entities.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r JOIN r.person JOIN r.movieInstance")
    List<Reservation> getReservationsBy_Ids(Long personId, Long movieInstanceId);

}
