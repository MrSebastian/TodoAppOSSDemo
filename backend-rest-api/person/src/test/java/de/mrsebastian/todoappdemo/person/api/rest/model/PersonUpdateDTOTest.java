package de.mrsebastian.todoappdemo.person.api.rest.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PersonUpdateDTOTest {

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
        void invalideWhenEMailIsNotInCorrectFormat() {
            val objectToValidate = initBuilderWithRequiredFields().email("invalideemailcom").build();

            val validationResult = validator.validate(objectToValidate);

            Assertions.assertThat(validationResult).hasSize(1);
        }

        @Test
        void invalideWhenEMailIsNull() {
            val objectToValidate = initBuilderWithRequiredFields().email(null).build();

            val validationResult = validator.validate(objectToValidate);

            Assertions.assertThat(validationResult).hasSize(1);
        }

        private PersonUpdateDTO.PersonUpdateDTOBuilder initBuilderWithRequiredFields() {
            return PersonUpdateDTO.builder().email("a@b.de");
        }
    }

}
