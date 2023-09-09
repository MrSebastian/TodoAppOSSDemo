package de.mrsebastian.todoappdemo.backend.task.domain;

import de.mrsebastian.todoappdemo.backend.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

    @NotNull
    private String title;

    private String description;

    private LocalDate dueDate;
}
