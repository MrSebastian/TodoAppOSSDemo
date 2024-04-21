package de.mrsebastian.todoappdemo.person.api.rest.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PersonDTO(@NotNull UUID id, String firstname, String lastname, @NotNull String email) {
}
