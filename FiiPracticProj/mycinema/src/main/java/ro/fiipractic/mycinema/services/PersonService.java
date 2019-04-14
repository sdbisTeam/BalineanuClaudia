package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.exceptions.NotFoundException;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();

    Person getPersonById(Long id) throws NotFoundException;

    Person savePerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Person person);
}
