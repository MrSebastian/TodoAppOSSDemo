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
                        :task-assignable-persons="persons"
                        @changed="handleTaskListChanged"
                        @change-requested="handleChangeRequested"
                    />
                </div>
            </v-col>
        </v-row>

        <base-task-create-dialog
            v-model="taskCreateDialogVisible"
            @added="handleNewTaskAdded"
        />

        <the-task-edit-dialog
            v-model="taskEditDialogVisible"
            :task="taskToEdit"
            @update:model-value="taskEditDialogVisibilityChanged"
            @edit="handleChangeSaveRequest"
        />
    </v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";

import HealthService from "@/api/HealthService";
import PersonService from "@/features/person/services/PersonService";
import PersonPersisted from "@/features/person/types/PersonPersisted";
import BaseTaskAddButton from "@/features/task/components/BaseTaskAddButton.vue";
import BaseTaskCreateDialog from "@/features/task/components/BaseTaskCreateDialog.vue";
import BaseTaskList from "@/features/task/components/BaseTaskList.vue";
import TheTaskEditDialog from "@/features/task/components/TheTaskEditDialog.vue";
import TaskService from "@/features/task/services/TaskService";
import TaskPersisted from "@/features/task/types/TaskPersisted";
import { useSnackbarStore } from "@/stores/snackbar";
import HealthState from "@/types/HealthState";

const snackbarStore = useSnackbarStore();
const status = ref("DOWN");

const personService = new PersonService();

const taskService = new TaskService();
const taskCreateDialogVisible = ref(false);
const taskEditDialogVisible = ref(false);
const taskToEdit = ref(TaskPersisted.createDefault());

const persons = ref<PersonPersisted[]>([]);
const tasks = ref<TaskPersisted[]>([]);

onMounted(() => {
    HealthService.checkHealth()
        .then((content: HealthState) => (status.value = content.status))
        .catch((error) => {
            snackbarStore.showMessage(error);
        });

    loadTasks();
    loadPersons();
});

function handleChangeSaveRequest(changedTask: TaskPersisted): void {
    taskService
        .updateTask(changedTask)
        .then(() => (taskEditDialogVisible.value = false))
        .then(() => loadTasks());
}

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

function loadPersons(): void {
    personService.getPersonen().then((result) => (persons.value = result));
}

function loadTasks(): void {
    taskService.getTasks().then((result) => (tasks.value = result));
}

function showTaskCreateDialog() {
    taskCreateDialogVisible.value = true;
}

function taskEditDialogVisibilityChanged(taskDialogVisible: boolean) {
    taskEditDialogVisible.value = taskDialogVisible;
}

function handleChangeRequested(taskToChange: TaskPersisted) {
    taskToEdit.value = taskToChange.clone();
    taskEditDialogVisible.value = true;
}
</script>
