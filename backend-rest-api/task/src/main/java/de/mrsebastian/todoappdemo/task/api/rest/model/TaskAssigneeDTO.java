package de.mrsebastian.todoappdemo.task.api.rest.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TaskAssigneeDTO(@NotNull UUID personId) {
}
