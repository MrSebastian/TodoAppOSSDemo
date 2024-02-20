<template>
    <v-container>
        <v-row class="text-center">
            <v-col cols="12"
                ><base-person-add-button @click="handlePersonAddClicked" />
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12">
                <div>
                    <v-btn
                        icon
                        variant="flat"
                        @click="handleReloadClicked"
                        ><v-icon>mdi-reload</v-icon></v-btn
                    >
                </div>
                <div>
                    <base-person-list :persons="persons" />
                </div>
            </v-col>
        </v-row>

        <the-person-create-dialog
            v-model="createDialogVisible"
            @added="handlePersonAdded"
        />
    </v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";

import BasePersonAddButton from "@/features/person/components/BasePersonAddButton.vue";
import BasePersonList from "@/features/person/components/BasePersonList.vue";
import ThePersonCreateDialog from "@/features/person/components/ThePersonCreateDialog.vue";
import PersonService from "@/features/person/services/PersonService";
import PersonPersisted from "@/features/person/types/PersonPersisted";

const createDialogVisible = ref(false);
const persons = ref<Array<PersonPersisted>>([]);
const personService = new PersonService();

onMounted(() => {
    loadPersons();
});

function handlePersonAdded() {
    loadPersons();
}

function handlePersonAddClicked() {
    showPersonCreateDialog();
}

function handleReloadClicked() {
    loadPersons();
}

function loadPersons() {
    personService
        .getPersonen()
        .then((loadedPersons) => (persons.value = loadedPersons));
}

function showPersonCreateDialog() {
    createDialogVisible.value = true;
}
</script>

<style scoped></style>
