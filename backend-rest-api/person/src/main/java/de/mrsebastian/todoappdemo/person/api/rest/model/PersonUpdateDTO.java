package de.mrsebastian.todoappdemo.person.api.rest.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PersonUpdateDTO(String firstname, String lastname, @NotNull @Email String email) {
}
