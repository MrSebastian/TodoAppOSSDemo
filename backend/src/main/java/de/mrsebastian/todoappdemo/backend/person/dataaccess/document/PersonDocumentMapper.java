package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonDocumentMapper {

    PersonDao toDao(PersonDocument personDocument);

    @Mapping(target = "id", ignore = true)
    PersonDocument toPersonDocument(PersonCreateDao personCreateDao);
}
