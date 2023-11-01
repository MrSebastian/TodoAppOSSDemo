package de.mrsebastian.todoappdemo.backend.task.dataaccess;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TaskCreateDao(@NotNull String title, @NotNull String description, LocalDate dueDate, UUID creatorId) {
}
