package de.mrsebastian.todoappdemo.backend.person.rest;

import de.mrsebastian.todoappdemo.backend.person.service.PersonService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Iterable<PersonDTO> getPersonen() {
        return personService.getPersonen();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createPerson(@Valid @RequestBody PersonCreateDTO personCreateDTO) {
        return personService.createPerson(personCreateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") UUID personId) {
        personService.deletePerson(personId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@PathVariable("id") UUID personId, @RequestBody PersonUpdateDTO personUpdateDTO) {
        personService.updatePerson(personId, personUpdateDTO);
    }

    @GetMapping("{id}")
    public PersonDTO getPerson(@PathVariable("id") UUID personId) {
        return personService.getPerson(personId);
    }
}
