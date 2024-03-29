package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
public class Person extends BaseEntity {

    @Size(max = 100)
    @ToString.Include
    private String firstname;

    @Size(max = 100)
    @ToString.Include
    private String lastname;

    @Size(max = 100)
    @NotNull
    @Email
    @ToString.Include
    private String email;
}
