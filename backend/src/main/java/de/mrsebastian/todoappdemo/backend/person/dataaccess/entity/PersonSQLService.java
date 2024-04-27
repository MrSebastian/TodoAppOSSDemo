package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDataAccessService;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonUpdateDao;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(Profiles.PROFILE_SQL)
@RequiredArgsConstructor
public class PersonSQLService implements PersonDataAccessService {

    private final PersonEntityMapper sqlDaoMapper;

    private final PersonRepository personRepository;

    public List<PersonDao> getPersonen() {
        return personRepository.findAll().stream().map(sqlDaoMapper::toDao).toList();
    }

    public PersonDao createPerson(final PersonCreateDao personCreateDao) {
        val entityToSave = sqlDaoMapper.toEntity(personCreateDao);
        return sqlDaoMapper.toDao(personRepository.save(entityToSave));
    }

    @Override
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }

    @Override
    public boolean personExists(UUID id) {
        return personRepository.existsById(id);
    }

    @Override
    public void updatePerson(UUID id, PersonUpdateDao personUpdateDao) {
        val personToUpdate = getPersonOrThrow(id);
        sqlDaoMapper.updateEntity(personUpdateDao, personToUpdate);
        personRepository.save(personToUpdate);
    }

    @Override
    public PersonDao getPerson(final UUID id) {
        return sqlDaoMapper.toDao(getPersonOrThrow(id));
    }

    private Person getPersonOrThrow(final UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException(id, Person.class));
    }
}
