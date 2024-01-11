<template>
    <v-list-item
        :title="props.task.title"
        :subtitle="dueDate"
    >
        <p>{{ props.task.description }}</p>

        <template #append>
            <div>
                <v-btn
                    icon
                    variant="flat"
                    @click="handleDeleteClicked"
                    ><v-icon>mdi-delete</v-icon></v-btn
                >
                <v-btn
                    icon
                    variant="flat"
                    @click="handleEditClicked"
                    ><v-icon>mdi-pencil</v-icon></v-btn
                >
            </div>
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
const emit = defineEmits<{
    (e: "delete", value: string): void;
    (e: "edit", task: TaskPersisted): void;
}>();

const dueDate = computed(() => (props.task.dueDate ? props.task.dueDate : "-"));

function handleDeleteClicked() {
    emit("delete", props.task.id);
}

function handleEditClicked() {
    emit("edit", props.task);
}
</script>
