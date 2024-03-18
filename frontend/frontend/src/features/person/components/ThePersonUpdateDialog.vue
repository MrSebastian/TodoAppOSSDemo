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
import PersonPersisted from "@/features/person/types/PersonPersisted";

interface IProps {
    modelValue: boolean;
    person: PersonPersisted;
}

const props = withDefaults(defineProps<IProps>(), {
    modelValue: false,
    person: () => PersonPersisted.createDefault(),
});

const emit = defineEmits<{
    (e: "update:modelValue", value: boolean): void;
    (e: "edit", editedPerson: PersonPersisted): void;
}>();

const validationUtils = useValidationUtils();
const refForm = ref<HTMLFormElement>();

const dialogVisible = computed({
    get: () => props.modelValue,
    set: (value: boolean) => emit("update:modelValue", value),
});

function closeDialog(): void {
    emit("update:modelValue", false);
    resetForm();
}

function handleCancelClicked(): void {
    closeDialog();
}

function handleSaveClicked(): void {
    validationUtils
        .proceedOnValid(refForm.value?.validate() as SubmitEventPromise)
        .then(() => emit("edit", props.person));
}

function resetForm() {
    refForm.value?.resetValidation();
}
</script>
