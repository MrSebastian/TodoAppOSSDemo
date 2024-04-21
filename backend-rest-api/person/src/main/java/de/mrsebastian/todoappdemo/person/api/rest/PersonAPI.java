package de.mrsebastian.todoappdemo.person.api.rest;

import de.mrsebastian.todoappdemo.person.api.rest.model.PersonCreateDTO;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonDTO;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonUpdateDTO;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonAPI {

    Iterable<PersonDTO> getPersonen();

    PersonDTO createPerson(@Valid @RequestBody PersonCreateDTO personCreateDTO);

    void deletePerson(@PathVariable("id") UUID personId);

    void updatePerson(@PathVariable("id") UUID personId, @RequestBody PersonUpdateDTO personUpdateDTO);
}
