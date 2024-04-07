package de.mrsebastian.todoappdemo.backend.person.service;

import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDataAccessService;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.entity.Person;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonCreateDTO;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonUpdateDTO;
import de.mrsebastian.todoappdemo.backend.person.service.events.PersonDeleteEvent;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonMapper personMapper;

    private final PersonDataAccessService personDAService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public List<PersonDTO> getPersonen() {
        return personDAService.getPersonen().stream().map(personMapper::toDto).toList();
    }

    public PersonDTO createPerson(final PersonCreateDTO personCreateDTO) {
        val personDaoForCreate = personMapper.toCreateDao(personCreateDTO);
        return personMapper.toDto(personDAService.createPerson(personDaoForCreate));
    }

    public void deletePerson(final UUID personId) {
        existsOrThrow(personId);
        personDAService.deletePerson(personId);
        applicationEventPublisher.publishEvent(new PersonDeleteEvent(personId));
    }

    public void existsOrThrow(final UUID personId) {
        if (!personDAService.personExists(personId)) {
            throw new NotFoundException(personId, Person.class);
        }
    }

    public void updatePerson(final UUID personId, final PersonUpdateDTO personUpdateDTO) {
        existsOrThrow(personId);
        personDAService.updatePerson(personId, personMapper.toUpdateDao(personUpdateDTO));
    }
}
