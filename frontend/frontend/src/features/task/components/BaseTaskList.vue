<template>
    <v-list>
        <base-task-list-item
            v-for="(task, index) in props.tasks"
            :key="index"
            :task="task"
            @delete="handleDeleteRequest"
            @edit="handleEditRequest"
        />
    </v-list>
</template>

<script setup lang="ts">
import BaseTaskListItem from "@/features/task/components/BaseTaskListItem.vue";
import TaskService from "@/features/task/services/TaskService";
import TaskPersisted from "@/features/task/types/TaskPersisted";

interface IProps {
    tasks: TaskPersisted[];
}

const taskService = new TaskService();

const props = defineProps<IProps>();
const emit = defineEmits<{
    (e: "changed"): void;
    (e: "changeRequested", taskToEdit: TaskPersisted): void;
}>();

function handleDeleteRequest(taskId: string) {
    taskService.deleteTask(taskId).then(() => emit("changed"));
}

function handleEditRequest(taskToEdit: TaskPersisted): void {
    emit("changeRequested", taskToEdit);
}
</script>
