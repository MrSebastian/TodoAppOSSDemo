package de.mrsebastian.todoappdemo.backend.task.api.rest.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record TaskDTO(@NotNull UUID id, @NotNull String title, @NotNull String description, LocalDate dueDate, UUID creator, UUID assigneeId) {
}
