<template>
    <div>
        <v-text-field
            v-model="task.title"
            label="Titel"
            :rules="rulesTitel"
        />
        <v-textarea
            v-model="task.description"
            label="Beschreibung"
            :counter="1000"
            :rules="rulesDescription"
        />
        <v-text-field
            v-model="task.dueDate"
            type="date"
            label="Due Date"
            clearable
        />
    </div>
</template>

<script setup lang="ts">
import { computed } from "vue";

import { useRules } from "@/composables/rules";
import Task from "@/features/task/types/Task";

interface IProps {
    value: Task;
}

const props = defineProps<IProps>();
const emit = defineEmits<(e: "input", value: Task) => void>();

const task = computed({
    get: () => props.value,
    set: (newValue: Task) => emit("input", newValue),
});

const rules = useRules();

const rulesTitel = [rules.required(), rules.maxlength(100)];
const rulesDescription = [rules.required(), rules.maxlength(1000)];
</script>
