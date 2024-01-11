<template>
    <v-dialog
        v-model="dialogVisible"
        :persistent="true"
        width="400px"
    >
        <v-card>
            <v-card-title>Task bearbeiten</v-card-title>
            <v-card-text>
                <v-form ref="refForm">
                    <base-task-fields :value="task" />
                </v-form>
            </v-card-text>
            <v-card-actions>
                <v-btn @click="handleCancelClicked">Abbrechen</v-btn>
                <v-btn @click="handleSaveClicked">Speichern</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script setup lang="ts">
import type { SubmitEventPromise } from "vuetify";

import { computed, ref } from "vue";

import { useValidationUtils } from "@/composables/useValidationUtils";
import BaseTaskFields from "@/features/task/components/BaseTaskFields.vue";
import TaskPersisted from "@/features/task/types/TaskPersisted";

const validationUtils = useValidationUtils();

interface IProps {
    modelValue: boolean;
    task: TaskPersisted;
}
const props = withDefaults(defineProps<IProps>(), {
    modelValue: false,
    task: () => TaskPersisted.createDefault(),
});

const emit = defineEmits<{
    (e: "update:modelValue", value: boolean): void;
    (e: "edit", editedTask: TaskPersisted): void;
}>();

const refForm = ref<HTMLFormElement>();

const dialogVisible = computed({
    get: () => props.modelValue,
    set: (value: boolean) => emit("update:modelValue", value),
});

function handleCancelClicked(): void {
    closeDialog();
}

function handleSaveClicked(): void {
    validationUtils
        .proceedOnValid(refForm.value?.validate() as SubmitEventPromise)
        .then(() => emit("edit", props.task));
}

function closeDialog(): void {
    emit("update:modelValue", false);
    resetForm();
}

function resetForm() {
    refForm.value?.resetValidation();
}
</script>
