package de.mrsebastian.todoappdemo.backend.graphql.types;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record Person(@NotNull UUID id, String firstname, String lastname, @NotNull String email) {
}
