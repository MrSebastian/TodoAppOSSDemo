package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.domain.BaseEntity;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BaseEntityTest {

    @Nested
    class equals {

        @Test
        void unequalWhenOtherIsNull() {
            Assertions.assertThat(new TestEntity().equals(null)).isFalse();
        }

        @Test
        void sameObjectsAreEqual() {
            val entity1 = new TestEntity();

            Assertions.assertThat(entity1.equals(entity1)).isTrue();
        }

        @Test
        void sameClassWithEqualIdAndIdNotNullAreEqual() {
            val testEntity1 = TestEntity.builder().id(UUID.randomUUID()).build();
            val testEntity2 = TestEntity.builder().id(UUID.fromString(testEntity1.getId().toString())).build();

            Assertions.assertThat(testEntity1.equals(testEntity2)).isTrue();
        }

        @Test
        void sameClassWithEqualIdAndIdIsNullAreUnequal() {
            val testEntity1 = TestEntity.builder().id(null).build();
            val testEntity2 = TestEntity.builder().id(null).build();

            Assertions.assertThat(testEntity1.equals(testEntity2)).isFalse();
        }

        @Test
        void sameClassWithUnequalIdAreUnequal() {
            val testEntity1 = TestEntity.builder().id(UUID.fromString("e8fc90c5-1862-41a6-92bc-8e182632821e")).build();
            val testEntity2 = TestEntity.builder().id(UUID.fromString("fac10170-80c3-4905-b87a-16c1b63a8659")).build();

            Assertions.assertThat(testEntity1.equals(testEntity2)).isFalse();
        }

        @Test
        void otherIsSubClassWithEqualIdIsEqual() {
            val testEntity = TestEntity.builder().id(UUID.randomUUID()).build();
            val subClassOfTestEntity = SubClassOfTestEntity.builder().id(UUID.fromString(testEntity.getId().toString())).boolProperty(true).build();

            Assertions.assertThat(testEntity.equals(subClassOfTestEntity)).isTrue();
        }

        @Test
        void otherIsSubClassWithUnequalIdIsUnequal() {
            val testEntity = TestEntity.builder().id(UUID.fromString("e8fc90c5-1862-41a6-92bc-8e182632821e")).build();
            val subClassOfTestEntity = SubClassOfTestEntity.builder().id(UUID.fromString("fac10170-80c3-4905-b87a-16c1b63a8659")).build();

            Assertions.assertThat(testEntity.equals(subClassOfTestEntity)).isFalse();
        }

        @Test
        void otherIsNonSubclassWithEqualIDIsUnequal() {
            val testEntity = TestEntity.builder().id(UUID.randomUUID()).build();
            val notSubclassOfTestEntity = NotSubclassOfTestEntity.builder().id(UUID.fromString(testEntity.getId().toString())).build();

            Assertions.assertThat(testEntity.equals(notSubclassOfTestEntity)).isFalse();
        }

        @Test
        void otherIsNonBaseEntityClassWithEqualIDIsUnequal() {
            val testEntity = TestEntity.builder().id(UUID.randomUUID()).build();
            val noneBaseEntityClass = NonBaseEntityClass.builder().id(UUID.fromString(testEntity.getId().toString())).build();

            Assertions.assertThat(testEntity.equals(noneBaseEntityClass)).isFalse();
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
    @NoArgsConstructor
    @SuperBuilder
    static class TestEntity extends BaseEntity {
        private String stringProperty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    static class SubClassOfTestEntity extends TestEntity {
        private boolean boolProperty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @SuperBuilder
    static class NotSubclassOfTestEntity extends BaseEntity {
        private Integer integerProperty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @SuperBuilder
    static class NonBaseEntityClass {
        private UUID id;
        private String stringProperty;
    }

}
