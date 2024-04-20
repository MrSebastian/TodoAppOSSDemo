package de.mrsebastian.todoappdemo.backend.task.api.rest.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TaskUpdateDTO(@NotNull String title, @NotNull String description, LocalDate dueDate) {
}
