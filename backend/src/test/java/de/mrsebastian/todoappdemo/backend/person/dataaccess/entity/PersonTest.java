package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PersonTest {

    @Nested
    class equals {

        @Test
        void unequalWhenOtherIsNull() {
            Assertions.assertThat(new Person().equals(null)).isFalse();
        }

        @Test
        void sameObjectsAreEqual() {
            val person = new Person();

            Assertions.assertThat(person.equals(person)).isTrue();
        }

        @Test
        void sameClassWithEqualIdAndIdNotNullAreEqual() {
            val person1 = Person.builder().id(UUID.randomUUID()).build();
            val person2 = Person.builder().id(UUID.fromString(person1.getId().toString())).build();

            Assertions.assertThat(person1.equals(person2)).isTrue();
        }

        @Test
        void sameClassWithEqualIdAndIdIsNullAreUnequal() {
            val person1 = Person.builder().id(null).build();
            val person2 = Person.builder().id(null).build();

            Assertions.assertThat(person1.equals(person2)).isFalse();
        }

        @Test
        void sameClassWithUnequalIdAreUnequal() {
            val person1 = Person.builder().id(UUID.fromString("e8fc90c5-1862-41a6-92bc-8e182632821e")).build();
            val person2 = Person.builder().id(UUID.fromString("fac10170-80c3-4905-b87a-16c1b63a8659")).build();

            Assertions.assertThat(person1.equals(person2)).isFalse();
        }

        @Test
        void otherIsSubClassWithEqualIdIsEqual() {
            val person1 = Person.builder().id(UUID.randomUUID()).build();
            val subClassOfPerson = TestEntity1.builder().id(UUID.fromString(person1.getId().toString())).build();

            Assertions.assertThat(person1.equals(subClassOfPerson)).isTrue();
        }

        @Test
        void otherIsSubClassWithUnequalIdIsUnequal() {
            val person1 = Person.builder().id(UUID.fromString("e8fc90c5-1862-41a6-92bc-8e182632821e")).build();
            val subClassOfPerson = TestEntity1.builder().id(UUID.fromString("fac10170-80c3-4905-b87a-16c1b63a8659")).build();

            Assertions.assertThat(person1.equals(subClassOfPerson)).isFalse();
        }

        @Test
        void otherIsNonSubclassWithEqualIDIsUnequal() {
            val person1 = Person.builder().id(UUID.randomUUID()).build();
            val subClassNotOfPerson = NonBaseEntityClass.builder().id(UUID.fromString(person1.getId().toString())).build();

            Assertions.assertThat(person1.equals(subClassNotOfPerson)).isFalse();
        }
    }

    @Nested
    class hashCode {

        @Test
        void onlyIdPropMattersForCalculation() {
            val person1 = Person.builder().email(null).firstname(null).lastname(null).id(UUID.randomUUID()).build();
            val person2 = Person.builder().email("email").firstname("firstname").lastname("lastname").id(UUID.fromString(person1.getId().toString())).build();

            Assertions.assertThat(person1.hashCode()).isEqualTo(person2.hashCode());
        }

        @Test
        void equalIdResultsInEqualHashcode() {
            val person1 = Person.builder().id(UUID.randomUUID()).build();
            val person2 = Person.builder().id(UUID.fromString(person1.getId().toString())).build();

            Assertions.assertThat(person1.hashCode()).isEqualTo(person2.hashCode());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @SuperBuilder
    static class TestEntity1 extends Person {
        final String stringProperty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @SuperBuilder
    static class NonBaseEntityClass {
        final UUID id;
        final String stringProperty;
    }

}
