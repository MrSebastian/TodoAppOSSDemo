package de.mrsebastian.todoappdemo.backend.task.dataaccess.entity;

import static java.sql.Types.VARCHAR;

import de.mrsebastian.todoappdemo.backend.domain.BaseEntity;
import de.mrsebastian.todoappdemo.backend.person.dataaccess.entity.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

    @NotNull
    @Size(min = 1)
    private String title;

    @NotNull
    @Size(max = 1000, min = 1)
    private String description;

    private LocalDate dueDate;

    @OneToOne
    @JoinColumn(name = "creator")
    private Person creator;

    @JdbcTypeCode(VARCHAR)
    private UUID assigneeId;
}
