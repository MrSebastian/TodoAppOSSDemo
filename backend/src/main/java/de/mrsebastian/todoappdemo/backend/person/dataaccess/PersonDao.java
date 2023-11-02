package de.mrsebastian.todoappdemo.backend.person.dataaccess;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PersonDao(@NotNull UUID id, String firstname, String lastname, @NotNull String email) {
}
