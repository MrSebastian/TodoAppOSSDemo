<template>
    <v-list-item
        :title="props.task.title"
        :subtitle="dueDate"
    >
        <p>{{ props.task.description }}</p>

        <template #append>
            <v-btn
                icon
                variant="flat"
                @click="handleDeleteClicked"
                ><v-icon>mdi-delete</v-icon></v-btn
            >
        </template>
    </v-list-item>
</template>

<script setup lang="ts">
import { computed, defineEmits, defineProps } from "vue";
import TaskPersisted from "@/features/task/types/TaskPersisted";

interface IProps {
    task: TaskPersisted;
}

const props = defineProps<IProps>();
const emit = defineEmits<(e: "delete", value: string) => void>();

const dueDate = computed(() => (props.task.dueDate ? props.task.dueDate : "-"));

function handleDeleteClicked() {
    emit("delete", props.task.id);
}
</script>
