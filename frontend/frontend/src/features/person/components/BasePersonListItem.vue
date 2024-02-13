<template>
    <v-list-item
        :title="fullname"
        :subtitle="props.person.id"
    ></v-list-item>
</template>

<script setup lang="ts">
import type PersonPersisted from "@/features/person/types/PersonPersisted";

interface IProps {
    person: PersonPersisted;
}

const props = defineProps<IProps>();

function fullname(): string {
    return `${props.person.firstname ?? ""} ${props.person.lastname ?? ""}`;
}
</script>

<style scoped></style>
