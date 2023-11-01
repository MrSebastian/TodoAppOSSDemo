package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import org.mapstruct.Mapper;

@Mapper
public interface PersonDocumentMapper {

    PersonDao toDao(PersonDocument personDocument);

    PersonDocument toPersonDocument(PersonCreateDao personCreateDao);
}
