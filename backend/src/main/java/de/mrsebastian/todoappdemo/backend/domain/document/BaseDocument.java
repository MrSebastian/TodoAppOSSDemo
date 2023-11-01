package de.mrsebastian.todoappdemo.backend.domain.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDocument {

    @Id
    private UUID id;
}
