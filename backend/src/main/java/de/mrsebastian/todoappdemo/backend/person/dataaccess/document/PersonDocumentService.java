package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonCreateDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDao;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.PersonDataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        return personDaoMapper.toDao(repository.save(personToCreate));
    }

    @Override
    public boolean personExists(UUID id) {
        return repository.existsById(id);
    }
}
