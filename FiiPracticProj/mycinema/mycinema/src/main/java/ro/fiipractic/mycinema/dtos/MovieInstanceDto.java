package ro.fiipractic.mycinema.dtos;

import java.sql.Timestamp;

public class MovieInstanceDto {

    private Long id;

    private Timestamp start_date;

    private Timestamp end_date;

    private Integer available_seats;

    private Long cinema;

    private Long movie;

    private Long movieRoom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public Integer getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Integer available_seats) {
        this.available_seats = available_seats;
    }

    public Long getCinema() {
        return cinema;
    }

    public void setCinema(Long cinema) {
        this.cinema = cinema;
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    public Long getMovieRoom() {
        return movieRoom;
    }

    public void setMovieRoom(Long movieRoom) {
        this.movieRoom = movieRoom;
    }
}
