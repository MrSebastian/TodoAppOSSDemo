package de.mrsebastian.todoappdemo.backend.person.service;

import de.mrsebastian.todoappdemo.backend.person.domain.PersonRepository;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonCreateDTO;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    public List<PersonDTO> getPersonen() {
        return personRepository.findAll().stream().map(personMapper::toDTO).toList();
    }

    public PersonDTO createPerson(final PersonCreateDTO personCreateDTO) {
        val entityToSave = personMapper.toEntity(personCreateDTO);
        return personMapper.toDTO(personRepository.save(entityToSave));
    }
}
