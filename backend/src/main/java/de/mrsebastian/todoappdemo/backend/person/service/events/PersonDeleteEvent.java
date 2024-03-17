package de.mrsebastian.todoappdemo.backend.person.service.events;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PersonDeleteEvent(@NotNull UUID personId) {
}
