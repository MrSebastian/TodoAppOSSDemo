<template>
    <v-dialog
        v-model="dialogVisible"
        :persistent="true"
        width="400px"
    >
        <v-card>
            <v-card-title>Task erstellen</v-card-title>
            <v-card-text>
                <v-form ref="refForm">
                    <base-task-fields :value="task" />
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
import { computed, ref } from "vue";

import BaseTaskFields from "@/features/task/components/BaseTaskFields.vue";

import TaskService from "@/features/task/services/TaskService";
import Task from "@/features/task/types/Task";
import { useValidationUtils } from "@/composables/useValidationUtils";
import type { SubmitEventPromise } from "vuetify";

const taskService = new TaskService();
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
const task = ref(Task.createDefault());

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
        .then(() => taskService.createTask(task.value))
        .then(() => closeDialog())
        .then(() => emit("added"));
}

function closeDialog(): void {
    emit("update:modelValue", false);
    resetForm();
}

function resetForm() {
    task.value = Task.createDefault();
    refForm.value?.resetValidation();
}
</script>
