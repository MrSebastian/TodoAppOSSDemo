package de.mrsebastian.todoappdemo.backend.person.dataaccess;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PersonCreateDao(String firstname, String lastname, @NotNull String email) {
}
