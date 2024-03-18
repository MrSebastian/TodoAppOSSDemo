package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonUpdateDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PersonEntityMapper {

    PersonDao toDao(Person personEntity);

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonCreateDao personCreateDao);

    @Mapping(target = "id", ignore = true)
    void updateEntity(PersonUpdateDao personUpdateDao, @MappingTarget Person personEntity);
}
