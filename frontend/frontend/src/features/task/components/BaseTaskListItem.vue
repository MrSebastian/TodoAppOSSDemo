<template>
    <v-list-item
        :title="props.task.title"
        :subtitle="dueDate"
    >
        <p>
            <base-person-selector
                :model-value="props.task.assigneeId"
                :available-persons="taskAssginablePersons"
                :clearable="true"
                :label="'Assignee'"
                @update:model-value="handleAssigneeChanged"
            />
        </p>
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
import type PersonPersisted from "@/features/person/types/PersonPersisted";

import { computed } from "vue";

import BaseDeleteIconButton from "@/components/common/buttons/icon/BaseDeleteIconButton.vue";
import BaseEditIconButton from "@/components/common/buttons/icon/BaseEditIconButton.vue";
import BasePersonSelector from "@/features/person/components/BasePersonSelector.vue";
import TaskPersisted from "@/features/task/types/TaskPersisted";

interface IProps {
    task: TaskPersisted;
    taskAssginablePersons: Array<PersonPersisted>;
}

const props = withDefaults(defineProps<IProps>(), {
    taskAssginablePersons: () => [],
});
const emit = defineEmits<{
    (e: "delete", value: string): void;
    (e: "edit", task: TaskPersisted): void;
    (e: "assigneeChanged", taskId: string, assigneeId: string | null): void;
}>();

const dueDate = computed(() => (props.task.dueDate ? props.task.dueDate : "-"));

function handleAssigneeChanged(assigneeId: string | null) {
    emit("assigneeChanged", props.task.id, assigneeId);
}
function handleDeleteClicked() {
    emit("delete", props.task.id);
}

function handleEditClicked() {
    emit("edit", props.task);
}
</script>
