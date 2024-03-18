package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

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
@Profile(Profiles.PROFILE_MONGO)
@RequiredArgsConstructor
public class PersonDocumentService implements PersonDataAccessService {

    private final PersonDocumentMapper personDaoMapper;

    private final PersonDocumentRepository repository;

    @Override
    public List<PersonDao> getPersonen() {
        return repository.findAll().stream().map(personDaoMapper::toDao).toList();
    }

    @Override
    public PersonDao createPerson(PersonCreateDao personCreateDao) {
        val personToCreate = personDaoMapper.toPersonDocument(personCreateDao);
        personToCreate.setId(UUID.randomUUID());
        return personDaoMapper.toDao(repository.save(personToCreate));
    }

    @Override
    public void deletePerson(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean personExists(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public void updatePerson(UUID id, PersonUpdateDao personUpdateDao) {
        val personToUpdate = getPersonOrThrow(id);
        personDaoMapper.updatePersonDocument(personUpdateDao, personToUpdate);
        repository.save(personToUpdate);
    }

    private PersonDocument getPersonOrThrow(final UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id, PersonDocument.class));
    }
}
