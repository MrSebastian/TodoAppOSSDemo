<template>
    <v-dialog
        v-model="dialogVisible"
        persistent
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
import { computed, defineProps, defineEmits, ref, withDefaults } from "vue";

import BaseTaskFields from "@/features/task/components/BaseTaskFields.vue";

import TaskService from "@/features/task/services/TaskService";
import Task from "@/features/task/types/Task";

const taskService = new TaskService();

interface IProps {
    value: boolean;
}
const props = withDefaults(defineProps<IProps>(), {
    value: false,
});

const emit = defineEmits<{
    (e: "input", value: boolean): void;
    (e: "added"): void;
}>();

const refForm = ref<HTMLFormElement>();
const task = ref(Task.createDefault());

const dialogVisible = computed({
    get: () => props.value,
    set: (value: boolean) => emit("input", value),
});

function handleCancelClicked(): void {
    closeDialog();
}

function handleSaveClicked(): void {
    if (refForm.value?.validate()) {
        taskService
            .createTask(task.value)
            .then(() => closeDialog())
            .then(() => emit("added"));
    }
}

function closeDialog(): void {
    emit("input", false);
    resetForm();
}

function resetForm() {
    task.value = Task.createDefault();
    refForm.value?.resetValidation();
}
</script>
