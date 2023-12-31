/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.mrsebastian.todoappdemo.backend;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestConstants {

    public static final String SPRING_TEST_PROFILE = "test";

    public static final String SPRING_NO_SECURITY_PROFILE = "no-security";

    public static final String SPRING_TEST_DB_H2 = "db-h2";

    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public static class TheEntityDto extends RepresentationModel {

        private String textAttribute;

    }

}
