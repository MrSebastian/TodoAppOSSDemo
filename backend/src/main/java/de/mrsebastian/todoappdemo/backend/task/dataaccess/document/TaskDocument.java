package de.mrsebastian.todoappdemo.backend.task.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.domain.document.BaseDocument;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
public class TaskDocument extends BaseDocument {

    @NotNull
    @Size(min = 1)
    private String title;

    @NotNull
    @Size(max = 1000, min = 1)
    private String description;

    private LocalDate dueDate;
}
