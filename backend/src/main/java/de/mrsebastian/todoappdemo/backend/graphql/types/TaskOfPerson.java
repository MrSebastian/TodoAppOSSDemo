package de.mrsebastian.todoappdemo.backend.graphql.types;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record TaskOfPerson(@NotNull UUID id, @NotNull String title, @NotNull String description, String dueDate,
                           UUID creatorId, UUID assigneeId) {
}
