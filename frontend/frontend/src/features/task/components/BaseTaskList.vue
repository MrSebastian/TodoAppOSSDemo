<template>
    <v-list>
        <base-task-list-item
            v-for="(task, index) in props.tasks"
            :key="index"
            :task="task"
            :task-assginable-persons="taskAssignablePersons"
            @delete="handleDeleteRequest"
            @edit="handleEditRequest"
            @assignee-changed="handleAssigneeChanged"
        />
    </v-list>
</template>

<script setup lang="ts">
import type PersonPersisted from "@/features/person/types/PersonPersisted";

import BaseTaskListItem from "@/features/task/components/BaseTaskListItem.vue";
import TaskService from "@/features/task/services/TaskService";
import TaskPersisted from "@/features/task/types/TaskPersisted";

interface IProps {
    tasks: TaskPersisted[];
    taskAssignablePersons: PersonPersisted[];
}

const taskService = new TaskService();

const props = defineProps<IProps>();
const emit = defineEmits<{
    (e: "changed"): void;
    (e: "changeRequested", taskToEdit: TaskPersisted): void;
    (e: "assigneeChanged", taskId: string, assigneeId: string | null): void;
}>();

function handleAssigneeChanged(taskId: string, assigneeId: string | null) {
    taskService
        .updateTaskAssignee(taskId, assigneeId)
        .then(() => emit("changed"));
}

function handleDeleteRequest(taskId: string) {
    taskService.deleteTask(taskId).then(() => emit("changed"));
}

function handleEditRequest(taskToEdit: TaskPersisted): void {
    emit("changeRequested", taskToEdit);
}
</script>
