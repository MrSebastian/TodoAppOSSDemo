package de.mrsebastian.todoappdemo.backend.task.rest;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record TaskDTO (@NotNull UUID id, @NotNull String title, String description, LocalDate dueDate) {
}
