<template>
    <v-list>
        <base-person-list-item
            v-for="(person, index) in props.persons"
            :key="index"
            :person="person"
            @delete="handleDeleteRequest"
        />
    </v-list>
</template>

<script setup lang="ts">
import type PersonPersisted from "@/features/person/types/PersonPersisted";

import BasePersonListItem from "@/features/person/components/BasePersonListItem.vue";

interface IProps {
    persons: PersonPersisted[];
}

const props = defineProps<IProps>();
const emit = defineEmits<{
    (e: "delete", value: string): void;
}>();

function handleDeleteRequest(personIdToDelete: string) {
    emit("delete", personIdToDelete);
}
</script>

<style scoped></style>
