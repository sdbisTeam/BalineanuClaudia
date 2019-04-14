package ro.fiipractic.mycinema.dtos;

public class ReservationDto {

    private Long id;

    private Integer number_of_tickets;

    private Long person;

    private Long movieInstance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber_of_tickets() {
        return number_of_tickets;
    }

    public void setNumber_of_tickets(Integer number_of_tickets) {
        this.number_of_tickets = number_of_tickets;
    }

    public Long getPerson() {
        return person;
    }

    public void setPerson(Long person) {
        this.person = person;
    }

    public Long getMovieInstance() {
        return movieInstance;
    }

    public void setMovieInstance(Long movieInstance) {
        this.movieInstance = movieInstance;
    }
}
