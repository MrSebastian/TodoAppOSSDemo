<template>
    <v-container>
        <v-row class="text-center">
            <v-col>
                <h1 class="text-h3 font-weight-bold mb-10">
                    willkommen - bienvenue - welcome
                </h1>
                <h3>Weiterführende Links sind hier zu finden:</h3>
                <div>
                    <a
                        href="https://www.youtube.com/watch?v=Ry_fR5H1GYw"
                        target="_blank"
                        rel="noopener noreferrer"
                        @click="dokumentationClicked = true"
                    >
                        In Ton und Bild
                    </a>
                </div>
            </v-col>
        </v-row>
        <yes-no-dialog
            v-model="saveLeaveDialog"
            dialogtitle="Wirklich verlassen?"
            dialogtext="Wollen Sie wirklich nicht die Dokumentation anschauen?"
            @no="cancel"
            @yes="leave"
        />
    </v-container>
</template>

<script setup lang="ts">
import YesNoDialog from "@/components/common/YesNoDialog.vue";
import { useSaveLeave } from "@/composables/saveLeave";
import { ref } from "vue";

const dokumentationClicked = ref(false);
const { cancel, leave, saveLeaveDialog } = useSaveLeave(isDirty);

function isDirty(): boolean {
    return !dokumentationClicked.value;
}
</script>
