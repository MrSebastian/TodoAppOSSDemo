package de.mrsebastian.todoappdemo.backend.person.dataaccess;

import java.util.List;
import java.util.UUID;

public interface PersonDataAccessService {

    List<PersonDao> getPersonen();

    PersonDao createPerson(PersonCreateDao personCreateDao);

    boolean personExists(UUID id);
}
