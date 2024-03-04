package de.mrsebastian.todoappdemo.backend.person.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.domain.document.BaseDocument;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class PersonDocument extends BaseDocument {

    @Size(max = 100)
    private String firstname;

    @Size(max = 100)
    private String lastname;

    @Size(max = 100)
    @NotNull
    @Email
    private String email;
}
