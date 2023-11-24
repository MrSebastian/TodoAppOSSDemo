<template>
    <v-container>
        <v-row class="text-center">
            <v-col cols="12">
                <base-task-add-button @click="handleTaskAddClicked" />
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12">
                <div>
                    <v-btn
                        icon
                        variant="flat"
                        @click="handleReloadClicked"
                        ><v-icon>mdi-reload</v-icon></v-btn
                    >
                    <base-task-list
                        :tasks="tasks"
                        @changed="handleTaskListChanged"
                    />
                </div>
            </v-col>
        </v-row>

        <base-task-create-dialog
            v-model="taskCreateDialogVisible"
            @added="handleNewTaskAdded"
        />
    </v-container>
</template>

<script setup lang="ts">
import HealthService from "@/api/HealthService";
import HealthState from "@/types/HealthState";
import { useSnackbarStore } from "@/stores/snackbar";
import { onMounted, ref } from "vue";

import BaseTaskAddButton from "@/features/task/components/BaseTaskAddButton.vue";
import BaseTaskCreateDialog from "@/features/task/components/BaseTaskCreateDialog.vue";
import BaseTaskList from "@/features/task/components/BaseTaskList.vue";

import TaskPersisted from "@/features/task/types/TaskPersisted";
import TaskService from "@/features/task/services/TaskService";

const snackbarStore = useSnackbarStore();
const status = ref("DOWN");

const taskService = new TaskService();
const taskCreateDialogVisible = ref(false);

const tasks = ref<TaskPersisted[]>([]);

onMounted(() => {
    HealthService.checkHealth()
        .then((content: HealthState) => (status.value = content.status))
        .catch((error) => {
            snackbarStore.showMessage(error);
        });

    loadTasks();
});

function handleNewTaskAdded(): void {
    loadTasks();
}

function handleTaskAddClicked(): void {
    showTaskCreateDialog();
}

function handleTaskListChanged(): void {
    loadTasks();
}

function handleReloadClicked(): void {
    loadTasks();
}

function loadTasks(): void {
    taskService.getTasks().then((result) => (tasks.value = result));
}

function showTaskCreateDialog() {
    taskCreateDialogVisible.value = true;
}
</script>
