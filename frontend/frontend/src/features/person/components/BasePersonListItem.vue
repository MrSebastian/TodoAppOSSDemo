<template>
    <v-list-item
        :title="fullname"
        :subtitle="props.person.id"
    >
        {{ props.person.email }}

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

interface IProps {
    person: PersonPersisted;
}

const props = defineProps<IProps>();
const emit = defineEmits<{
    (e: "delete", value: string): void;
    (e: "edit", value: PersonPersisted): void;
}>();

const fullname = computed(
    () => `${props.person.firstname ?? ""} ${props.person.lastname ?? ""}`
);

function handleDeleteClicked() {
    emit("delete", props.person.id);
}

function handleEditClicked() {
    emit("edit", props.person);
}
</script>

<style scoped></style>
