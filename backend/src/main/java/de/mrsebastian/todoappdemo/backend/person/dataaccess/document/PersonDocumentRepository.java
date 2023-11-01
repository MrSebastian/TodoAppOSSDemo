package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PersonDocumentRepository extends MongoRepository<PersonDocument, UUID> {
}
