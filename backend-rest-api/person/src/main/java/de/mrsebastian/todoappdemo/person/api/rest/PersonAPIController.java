package de.mrsebastian.todoappdemo.person.api.rest;

import de.mrsebastian.todoappdemo.person.api.rest.model.PersonCreateDTO;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonDTO;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonUpdateDTO;
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
@RequestMapping("${todoapp.api.person.rest.base-path:/api/person}")
@RequiredArgsConstructor
public class PersonAPIController {

    private final PersonAPI personAPI;

    @GetMapping
    public Iterable<PersonDTO> getPersonen() {
        return personAPI.getPersonen();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createPerson(@Valid @RequestBody final PersonCreateDTO personCreateDTO) {
        return personAPI.createPerson(personCreateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable("id") final UUID personId) {
        personAPI.deletePerson(personId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@PathVariable("id") final UUID personId, @RequestBody final PersonUpdateDTO personUpdateDTO) {
        personAPI.updatePerson(personId, personUpdateDTO);
    }
}
