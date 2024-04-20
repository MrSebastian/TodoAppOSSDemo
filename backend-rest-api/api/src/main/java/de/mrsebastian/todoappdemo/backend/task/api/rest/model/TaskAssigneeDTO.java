package de.mrsebastian.todoappdemo.backend.task.api.rest.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record TaskAssigneeDTO(@NotNull UUID personId) {
}
