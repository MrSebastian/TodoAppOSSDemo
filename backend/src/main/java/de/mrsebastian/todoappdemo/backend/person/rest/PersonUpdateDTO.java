package de.mrsebastian.todoappdemo.backend.person.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PersonUpdateDTO(String firstname, String lastname, @NotNull @Email String email) {
}
