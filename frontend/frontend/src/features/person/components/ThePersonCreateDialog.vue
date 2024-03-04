<template>
    <v-dialog
        v-model="dialogVisible"
        :persistent="true"
        width="400px"
    >
        <v-card>
            <v-card-title>Person erstellen</v-card-title>
            <v-card-text>
                <v-form ref="refForm">
                    <base-person-fields :person="person" />
                </v-form>
            </v-card-text>
            <v-card-actions>
                <v-btn @click="handleCancelClicked">Abbrechen</v-btn>
                <v-btn @click="handleSaveClicked">Erstellen</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script setup lang="ts">
import type { SubmitEventPromise } from "vuetify";

import { computed, ref } from "vue";

import { useValidationUtils } from "@/composables/useValidationUtils";
import BasePersonFields from "@/features/person/components/BasePersonFields.vue";
import PersonService from "@/features/person/services/PersonService";
import Person from "@/features/person/types/Person";

const personService = new PersonService();
const validationUtils = useValidationUtils();

interface IProps {
    modelValue: boolean;
}

const props = withDefaults(defineProps<IProps>(), {
    modelValue: false,
});

const emit = defineEmits<{
    (e: "update:modelValue", value: boolean): void;
    (e: "added"): void;
}>();

const refForm = ref<HTMLFormElement>();
const person = ref(Person.createDefault());

const dialogVisible = computed({
    get: () => props.modelValue,
    set: (value: boolean) => emit("update:modelValue", value),
});

function closeDialog(): void {
    emit("update:modelValue", false);
    resetForm();
}

function handleCancelClicked() {
    closeDialog();
}

function handleSaveClicked() {
    validationUtils
        .proceedOnValid(refForm.value?.validate() as SubmitEventPromise)
        .then(() => personService.createPerson(person.value))
        .then(() => closeDialog())
        .then(() => emit("added"));
}

function resetForm() {
    person.value = Person.createDefault();
    refForm.value?.resetValidation();
}
</script>

<style scoped></style>
