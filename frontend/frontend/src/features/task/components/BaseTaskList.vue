<template>
    <v-list>
        <base-task-list-item
            v-for="(task, index) in props.tasks"
            :key="index"
            :task="task"
            @delete="handleDeleteRequest"
        />
    </v-list>
</template>

<script setup lang="ts">
import { defineEmits, defineProps } from "vue";

import BaseTaskListItem from "@/features/task/components/BaseTaskListItem.vue";
import TaskService from "@/features/task/services/TaskService";
import TaskPersisted from "@/features/task/types/TaskPersisted";

interface IProps {
    tasks: TaskPersisted[];
}

const taskService = new TaskService();

const props = defineProps<IProps>();
const emit = defineEmits<(e: "changed") => void>();

function handleDeleteRequest(taskId: string) {
    taskService.deleteTask(taskId).then(() => emit("changed"));
}
</script>
