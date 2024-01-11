package de.mrsebastian.todoappdemo.backend.task.dataaccess;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskUpdateDao(@NotNull String title, @NotNull String description, LocalDate dueDate) {
}
