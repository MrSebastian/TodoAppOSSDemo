package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import org.mapstruct.Mapper;

@Mapper
public interface PersonEntityMapper {

    PersonDao toDao(Person personEntity);

    Person toEntity(PersonCreateDao personCreateDao);
}
