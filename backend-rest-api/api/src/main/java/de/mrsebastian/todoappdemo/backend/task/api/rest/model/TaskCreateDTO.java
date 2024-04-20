package de.mrsebastian.todoappdemo.backend.task.api.rest.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TaskCreateDTO(@NotNull String title, @NotNull String description, LocalDate dueDate, UUID creatorId) {
}
