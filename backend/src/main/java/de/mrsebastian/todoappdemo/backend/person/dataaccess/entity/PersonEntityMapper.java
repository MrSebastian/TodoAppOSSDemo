package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonEntityMapper {

    PersonDao toDao(Person personEntity);

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonCreateDao personCreateDao);
}
