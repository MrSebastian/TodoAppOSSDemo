package de.mrsebastian.todoappdemo.backend.task.dataaccess;

import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TaskDao(@NotNull UUID id, @NotNull String title, @NotNull String description, LocalDate dueDate, PersonDTO creator, UUID assigneeId) {
}
