<template>
    <div>
        <v-text-field
            v-model="person.email"
            label="EMail"
        />
    </div>
</template>

<script setup lang="ts">
import type Person from "@/features/person/types/Person";

import { computed } from "vue";

interface IProps {
    person: Person;
}

const props = defineProps<IProps>();
const emit = defineEmits<{
    (e: "update:modelValue", value: Person): void;
}>();

const person = computed({
    get: () => props.person,
    set: (newValue: Person) => emit("update:modelValue", newValue),
});
</script>

<style scoped></style>
