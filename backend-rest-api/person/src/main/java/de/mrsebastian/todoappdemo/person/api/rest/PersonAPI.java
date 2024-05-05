package de.mrsebastian.todoappdemo.person.api.rest;

import de.mrsebastian.todoappdemo.person.api.rest.model.PersonCreateDTO;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonDTO;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonUpdateDTO;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestBody;

public interface PersonAPI {

    Iterable<PersonDTO> getPersonen();

    PersonDTO createPerson(PersonCreateDTO personCreateDTO);

    void deletePerson(UUID personId);

    void updatePerson(UUID personId, @RequestBody PersonUpdateDTO personUpdateDTO);
}
