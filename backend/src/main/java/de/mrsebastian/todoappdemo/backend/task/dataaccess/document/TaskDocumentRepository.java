package de.mrsebastian.todoappdemo.backend.task.dataaccess.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TaskDocumentRepository extends MongoRepository<TaskDocument, UUID> {
}
