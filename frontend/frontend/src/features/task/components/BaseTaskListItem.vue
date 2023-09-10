<template>
    <v-list-item>
        <v-list-item-content>
            <v-list-item-title>{{ props.task.title }}</v-list-item-title>
            <v-list-item-subtitle>{{ dueDate }}</v-list-item-subtitle>
            <p>{{ props.task.description }}</p>
        </v-list-item-content>
        <v-list-item-action>
            <v-btn
                icon
                @click="handleDeleteClicked"
                ><v-icon>mdi-delete</v-icon></v-btn
            >
        </v-list-item-action>
    </v-list-item>
</template>

<script setup lang="ts">
import { computed, defineEmits, defineProps } from "vue";
import TaskPersisted from "@/features/task/types/TaskPersisted";

interface IProps {
    task: TaskPersisted;
}

const props = defineProps<IProps>();
const emit = defineEmits<{
    (e: "delete", value: string): void;
}>();

const dueDate = computed(() => (props.task.dueDate ? props.task.dueDate : "-"));

function handleDeleteClicked() {
    emit("delete", props.task.id);
}
</script>
