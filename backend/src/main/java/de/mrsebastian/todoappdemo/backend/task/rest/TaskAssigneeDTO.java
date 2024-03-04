package de.mrsebastian.todoappdemo.backend.task.rest;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record TaskAssigneeDTO(@NotNull UUID personId) {
}
