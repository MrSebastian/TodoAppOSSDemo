package de.mrsebastian.todoappdemo.backend.exception;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class NotFoundException extends RuntimeException {

    private static final String ERR_MSG_TEMPLATE = "entity of type %s with id %s not found";

    private final UUID id;

    private final Class<?> entityClass;

    public NotFoundException(final UUID id, final Class<?> entityClass) {
        super(String.format(ERR_MSG_TEMPLATE, entityClass.getCanonicalName(), id));
        this.id = id;
        this.entityClass = entityClass;
    }
}
