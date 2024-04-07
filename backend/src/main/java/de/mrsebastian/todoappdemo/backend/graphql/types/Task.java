package de.mrsebastian.todoappdemo.backend.graphql.types;

import de.mrsebastian.todoappdemo.backend.person.dataaccess.entity.Person;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record Task(@NotNull UUID id, @NotNull String title, @NotNull String description, String dueDate,
                   Person creator, UUID assigneeId, Person assignee) {
}
