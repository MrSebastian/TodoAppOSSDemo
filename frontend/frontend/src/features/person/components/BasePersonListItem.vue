<template>
    <v-list-item
        :title="fullname"
        :subtitle="props.person.id"
    >
        {{ props.person.email }}
    </v-list-item>
</template>

<script setup lang="ts">
import type PersonPersisted from "@/features/person/types/PersonPersisted";

import { computed } from "vue";

interface IProps {
    person: PersonPersisted;
}

const props = defineProps<IProps>();

const fullname = computed(
    () => `${props.person.firstname ?? ""} ${props.person.lastname ?? ""}`
);
</script>

<style scoped></style>
