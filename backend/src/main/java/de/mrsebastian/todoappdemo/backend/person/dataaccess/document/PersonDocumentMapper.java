package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonUpdateDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PersonDocumentMapper {

    PersonDao toDao(PersonDocument personDocument);

    @Mapping(target = "id", ignore = true)
    PersonDocument toPersonDocument(PersonCreateDao personCreateDao);

    @Mapping(target = "id", ignore = true)
    void updatePersonDocument(PersonUpdateDao personUpdateDao, @MappingTarget PersonDocument personDocument);
}
