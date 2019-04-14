package ro.fiipractic.mycinema.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number_of_tickets;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Person person;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "movie_instance_id")
    private MovieInstance movieInstance;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public MovieInstance getMovieInstance() {
        return movieInstance;
    }

    public void setMovieInstance(MovieInstance movieInstance) {
        this.movieInstance = movieInstance;
    }
}
