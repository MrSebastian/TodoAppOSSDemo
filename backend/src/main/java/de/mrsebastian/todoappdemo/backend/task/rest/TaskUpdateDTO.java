package de.mrsebastian.todoappdemo.backend.task.rest;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TaskUpdateDTO(@NotNull String title, @NotNull String description, LocalDate dueDate) {
}
