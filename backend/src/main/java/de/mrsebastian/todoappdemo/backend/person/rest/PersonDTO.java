package de.mrsebastian.todoappdemo.backend.person.rest;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PersonDTO(@NotNull UUID id, String firstname, String lastname, @NotNull String email) {
}
