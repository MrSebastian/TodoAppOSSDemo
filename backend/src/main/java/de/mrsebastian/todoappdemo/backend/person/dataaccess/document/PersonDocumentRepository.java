package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

@Profile(Profiles.PROFILE_MONGO)
public interface PersonDocumentRepository extends MongoRepository<PersonDocument, UUID> {
}
