package de.mrsebastian.todoappdemo.backend.task.rest;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record TaskDTO(@NotNull UUID id, @NotNull String title, @NotNull String description, LocalDate dueDate, UUID creatorId, UUID assigneeId) {
}
