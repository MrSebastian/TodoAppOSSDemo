package de.mrsebastian.todoappdemo.backend.task.dataaccess;

import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TaskDao(@NotNull UUID id, @NotNull String title, @NotNull String description, LocalDate dueDate, PersonDTO creator) {
}
