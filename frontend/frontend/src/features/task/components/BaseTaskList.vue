<template>
    <v-list>
        <base-task-list-item
            v-for="(task, index) in props.tasks"
            :key="index"
            :task="task"
        />
    </v-list>
</template>

<script setup lang="ts">
import { defineProps } from "vue";
import TaskPersisted from "@/features/task/types/TaskPersisted";

import BaseTaskListItem from "@/features/task/components/BaseTaskListItem.vue";

interface IProps {
    tasks: TaskPersisted[];
}

const props = defineProps<IProps>();
</script>
