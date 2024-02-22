package de.mrsebastian.todoappdemo.backend.person.dataaccess;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PersonDao(@NotNull UUID id, String firstname, String lastname, @NotNull String email) {
}
