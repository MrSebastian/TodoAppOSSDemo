<template>
    <v-autocomplete
        :items="props.availablePersons"
        :item-title="'email'"
        :item-value="'id'"
    >
    </v-autocomplete>
</template>

<script setup lang="ts">
import type PersonPersisted from "@/features/person/types/PersonPersisted";

interface IProps {
    availablePersons: Array<PersonPersisted>;
}

const props = withDefaults(defineProps<IProps>(), {
    availablePersons: () => [],
});
</script>

<style scoped></style>
