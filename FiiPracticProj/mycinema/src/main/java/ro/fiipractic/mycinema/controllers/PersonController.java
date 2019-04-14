package ro.fiipractic.mycinema.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.exceptions.BadRequestException;
import ro.fiipractic.mycinema.exceptions.NotFoundException;
import ro.fiipractic.mycinema.services.PersonService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable("id") Long id) throws NotFoundException {
        return personService.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person personToSave) throws URISyntaxException {
        Person person = personService.savePerson(modelMapper.map(personToSave, Person.class));

        return ResponseEntity.created(new URI("/api/persons/" + person.getId())).body(person);
    }

    @PutMapping(value = "/{id}")
    public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person personToUpdate) throws NotFoundException, BadRequestException {
        if(!id.equals(personToUpdate.getId())){
            throw new BadRequestException("Different ids: " + id + " from PathVariable and " + personToUpdate.getId() + " from RequestBody");
        }
        Person personDb = personService.getPersonById(id);

        modelMapper.map(personToUpdate, personDb);

        return personService.updatePerson(personDb);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable Long id) throws NotFoundException {
        Person personDb = personService.getPersonById(id);

        personService.deletePerson(personDb);
    }
}
