package de.mrsebastian.todoappdemo.backend.task.rest;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskCreateDTO(@NotNull String title, @NotNull String description, LocalDate dueDate) {
}
