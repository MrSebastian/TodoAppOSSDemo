<template>
    <v-container>
        <v-row class="text-center">
            <v-col cols="12">
                <base-person-add-button @click="handlePersonAddClicked" />
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="12">
                <div>
                    <v-btn
                        icon
                        variant="flat"
                        @click="handleReloadClicked"
                    >
                        <v-icon>mdi-reload</v-icon>
                    </v-btn>
                </div>
                <div>
                    <base-person-list
                        :persons="persons"
                        @delete="handlePersonDeleteRequest"
                        @edit="handlePersonEditRequest"
                    />
                </div>
            </v-col>
        </v-row>

        <the-person-create-dialog
            v-model="createDialogVisible"
            @added="handlePersonAdded"
        />

        <the-person-update-dialog
            v-model="updateDialogVisible"
            :person="personToUpdate"
            @edit="handlePersonUpdateAccepted"
        />
    </v-container>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";

import BasePersonAddButton from "@/features/person/components/BasePersonAddButton.vue";
import BasePersonList from "@/features/person/components/BasePersonList.vue";
import ThePersonCreateDialog from "@/features/person/components/ThePersonCreateDialog.vue";
import ThePersonUpdateDialog from "@/features/person/components/ThePersonUpdateDialog.vue";
import PersonService from "@/features/person/services/PersonService";
import PersonPersisted from "@/features/person/types/PersonPersisted";

const createDialogVisible = ref(false);
const persons = ref<Array<PersonPersisted>>([]);
const personService = new PersonService();
const updateDialogVisible = ref(false);
const personToUpdate = ref(PersonPersisted.createDefault());

onMounted(() => {
    loadPersons();
});

function handlePersonAdded() {
    loadPersons();
}

function handlePersonDeleteRequest(personIdForDeletion: string): void {
    personService.deletePerson(personIdForDeletion).then(() => loadPersons());
}

function handlePersonAddClicked() {
    showPersonCreateDialog();
}

function handlePersonEditRequest(personToEdit: PersonPersisted) {
    personToUpdate.value = personToEdit.clone();
    showPersonEditDialog();
}

function handlePersonUpdateAccepted(personUpdateData: PersonPersisted) {
    personService
        .updatePerson(personUpdateData)
        .then(() => (updateDialogVisible.value = false))
        .then(() => loadPersons());
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

function showPersonEditDialog() {
    updateDialogVisible.value = true;
}
</script>

<style scoped></style>
