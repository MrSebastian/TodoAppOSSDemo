/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.mrsebastian.todoappdemo.backend.domain;

import static java.sql.Types.VARCHAR;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.val;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class BaseEntity implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id", length = 36)
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @JdbcTypeCode(VARCHAR)
    @ToString.Include
    private UUID id;

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(final Object otherEntity) {
        return otherEntityIsSame(otherEntity) || isCompatibleClassWithEqualNonNullId(otherEntity);
    }

    private boolean otherEntityIsSame(final Object otherEntity) {
        return this == otherEntity;
    }

    private boolean isCompatibleClassWithEqualNonNullId(Object otherEntity) {
        return (otherEntity instanceof BaseEntity otherAsBaseEntity) && otherEntityIsAnInstanceOfOurEntity(otherEntity)
                && idsAreEqualAndNonNull(otherAsBaseEntity);
    }

    private boolean otherEntityIsAnInstanceOfOurEntity(final Object otherEntity) {
        return this.getClass().isInstance(otherEntity);
    }

    private boolean idsAreEqualAndNonNull(final BaseEntity otherBaseEntity) {
        val bothIdsAreNonNull = this.getId() != null && otherBaseEntity.getId() != null;
        val bothIdsAreEqual = Objects.equals(this.getId(), otherBaseEntity.getId());

        return bothIdsAreNonNull && bothIdsAreEqual;
    }

}
