<template>
    <v-list> </v-list>
</template>

<script setup lang="ts">
import type PersonPersisted from "@/features/person/types/PersonPersisted";

interface IProps {
    persons: PersonPersisted[];
}

const props = defineProps<IProps>();
</script>

<style scoped></style>
