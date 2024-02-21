package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

@Profile(Profiles.PROFILE_MONGO)
public interface PersonDocumentRepository extends MongoRepository<PersonDocument, UUID> {
}
