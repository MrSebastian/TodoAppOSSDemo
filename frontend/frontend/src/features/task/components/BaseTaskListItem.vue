<template>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title>{{ props.task.title }}</v-list-item-title>
            <v-list-item-subtitle>{{ dueDate }}</v-list-item-subtitle>
            <p>{{ props.task.description }}</p>
        </v-list-item-content>
    </v-list-item>
</template>

<script setup lang="ts">
import { computed, defineProps } from "vue";
import TaskPersisted from "@/features/task/types/TaskPersisted";

interface IProps {
    task: TaskPersisted;
}

const props = defineProps<IProps>();

const dueDate = computed(() => (props.task.dueDate ? props.task.dueDate : "-"));
</script>
