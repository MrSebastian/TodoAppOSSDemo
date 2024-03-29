package de.mrsebastian.todoappdemo.backend.person.service;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonUpdateDao;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonCreateDTO;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import de.mrsebastian.todoappdemo.backend.person.rest.PersonUpdateDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    PersonDTO toDto(PersonDao entity);

    PersonCreateDao toCreateDao(PersonCreateDTO createDTO);

    PersonUpdateDao toUpdateDao(PersonUpdateDTO updateDTO);
}
