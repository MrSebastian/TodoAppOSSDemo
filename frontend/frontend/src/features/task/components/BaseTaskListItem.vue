<template>
    <v-list-item
        :title="props.task.title"
        :subtitle="dueDate"
    >
        <p>{{ props.task.description }}</p>

        <template #append>
            <div>
                <base-delete-icon-button @click="handleDeleteClicked" />
                <base-edit-icon-button @click="handleEditClicked" />
            </div>
        </template>
    </v-list-item>
</template>

<script setup lang="ts">
import { computed } from "vue";

import BaseDeleteIconButton from "@/components/common/buttons/icon/BaseDeleteIconButton.vue";
import BaseEditIconButton from "@/components/common/buttons/icon/BaseEditIconButton.vue";
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
