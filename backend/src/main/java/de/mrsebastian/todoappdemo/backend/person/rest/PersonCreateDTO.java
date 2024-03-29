package de.mrsebastian.todoappdemo.backend.person.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PersonCreateDTO(String firstname, String lastname, @NotNull @Email String email) {
}
