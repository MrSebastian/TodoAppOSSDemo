<template>
    <v-list-item
        :title="fullname"
        :subtitle="props.person.id"
    >
        {{ props.person.email }}

        <template #append>
            <div>
                <base-delete-icon-button @click="handleDeleteClicked" />
            </div>
        </template>
    </v-list-item>
</template>

<script setup lang="ts">
import type PersonPersisted from "@/features/person/types/PersonPersisted";

import { computed } from "vue";

import BaseDeleteIconButton from "@/components/common/buttons/icon/BaseDeleteIconButton.vue";

interface IProps {
    person: PersonPersisted;
}

const props = defineProps<IProps>();
const emit = defineEmits<{
    (e: "delete", value: string): void;
}>();

const fullname = computed(
    () => `${props.person.firstname ?? ""} ${props.person.lastname ?? ""}`
);

function handleDeleteClicked() {
    emit("delete", props.person.id);
}
</script>

<style scoped></style>
