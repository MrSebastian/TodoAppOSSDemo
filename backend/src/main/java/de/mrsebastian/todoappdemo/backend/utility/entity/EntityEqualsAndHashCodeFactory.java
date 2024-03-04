package de.mrsebastian.todoappdemo.backend.utility.entity;

import de.mrsebastian.todoappdemo.backend.domain.BaseEntity;
import java.util.Objects;
import lombok.val;

public record EntityEqualsAndHashCodeFactory(BaseEntity entity) {

    public int getHashcode() {
        return Objects.hash(entity.getId());
    }

    public boolean isEqual(final Object otherEntity) {
        return otherEntityIsSame(otherEntity) || isCompatibleClassWithEqualNonNullId(otherEntity);
    }

    private boolean otherEntityIsSame(final Object otherEntity) {
        return entity == otherEntity;
    }

    private boolean isCompatibleClassWithEqualNonNullId(Object otherEntity) {
        return (otherEntity instanceof BaseEntity otherAsBaseEntity) && otherEntityIsAnInstanceOfOurEntity(otherEntity) && idsAreEqualAndNonNull(otherAsBaseEntity);
    }

    private boolean otherEntityIsAnInstanceOfOurEntity(final Object otherEntity) {
        return entity.getClass().isInstance(otherEntity);
    }

    private boolean idsAreEqualAndNonNull(final BaseEntity otherBaseEntity) {
        val bothIdsAreNonNull = entity.getId() != null && otherBaseEntity.getId() != null;
        val bothIdsAreEqual = Objects.equals(entity.getId(), otherBaseEntity.getId());

        return bothIdsAreNonNull && bothIdsAreEqual;
    }
}
