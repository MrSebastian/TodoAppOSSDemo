package de.mrsebastian.todoappdemo.backend.domain.document;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDocument {

    @Id
    private UUID id;
}
