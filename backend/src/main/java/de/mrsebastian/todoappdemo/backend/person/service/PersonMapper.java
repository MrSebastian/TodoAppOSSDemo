package de.mrsebastian.todoappdemo.backend.person.service;

import de.mrsebastian.todoappdemo.backend.person.domain.Person;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonCreateDTO;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    PersonDTO toDTO(Person entity);

    Person toEntity(PersonCreateDTO createDTO);
}
