package de.mrsebastian.todoappdemo.backend.person.service;

import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDataAccessService;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.entity.Person;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonCreateDTO;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonMapper personMapper;

    private final PersonDataAccessService personDAService;

    public List<PersonDTO> getPersonen() {
        return personDAService.getPersonen().stream().map(personMapper::toDto).toList();
    }

    public PersonDTO createPerson(final PersonCreateDTO personCreateDTO) {
        val personDaoForCreate = personMapper.toCreateDao(personCreateDTO);
        return personMapper.toDto(personDAService.createPerson(personDaoForCreate));
    }

    public void existsOrThrow(final UUID personId) {
        if (!personDAService.personExists(personId)) {
            throw new NotFoundException(personId, Person.class);
        }
    }
}
