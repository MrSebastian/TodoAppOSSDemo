package de.mrsebastian.todoappdemo.task.api.rest.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.UUID;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TaskDTOTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Nested
    class ValidationTests {

        @Test
        void allRequiredFieldsAreSet() {
            val objectToValidate = initBuilderWithRequiredFields().build();

            val validationResult = validator.validate(objectToValidate);

            Assertions.assertThat(validationResult).isEmpty();
        }

        @Test
        void invalideWhenIdIsNull() {
            val objectToValidate = initBuilderWithRequiredFields().id(null).build();

            val validationResult = validator.validate(objectToValidate);

            Assertions.assertThat(validationResult).hasSize(1);
        }

        @Test
        void invalideWhenTitleIsNull() {
            val objectToValidate = initBuilderWithRequiredFields().title(null).build();

            val validationResult = validator.validate(objectToValidate);

            Assertions.assertThat(validationResult).hasSize(1);
        }

        @Test
        void invalideWhenDescriptionIsNull() {
            val objectToValidate = initBuilderWithRequiredFields().description(null).build();

            val validationResult = validator.validate(objectToValidate);

            Assertions.assertThat(validationResult).hasSize(1);
        }

        private TaskDTO.TaskDTOBuilder initBuilderWithRequiredFields() {
            return TaskDTO.builder().id(UUID.randomUUID()).title("title").description("description");
        }
    }
}
