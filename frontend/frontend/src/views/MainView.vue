<template>
    <v-container>
        <v-row class="text-center">
            <v-col cols="12">
                <base-task-add-button @click="handleTaskAddClicked" />
            </v-col>

            <v-col class="mb-4">
                <h1 class="text-h3 font-weight-bold mb-3">
                    Willkommen beim RefArch-Kickstarter
                </h1>
                <p>
                    Das API-Gateway ist:
                    <span :class="status">{{ status }}</span>
                </p>
            </v-col>
        </v-row>

        <base-task-create-dialog v-model="taskCreateDialogVisible" />
    </v-container>
</template>

<script setup lang="ts">
import HealthService from "@/api/HealthService";
import HealthState from "@/types/HealthState";
import { useSnackbarStore } from "@/stores/snackbar";
import { onMounted, ref } from "vue";

import BaseTaskAddButton from "@/features/task/components/BaseTaskAddButton.vue";
import BaseTaskCreateDialog from "@/features/task/components/BaseTaskCreateDialog.vue";

const snackbarStore = useSnackbarStore();
const status = ref("DOWN");
const taskCreateDialogVisible = ref(false);

onMounted(() => {
    HealthService.checkHealth()
        .then((content: HealthState) => (status.value = content.status))
        .catch((error) => {
            snackbarStore.showMessage(error);
        });
});

function handleTaskAddClicked(): void {
    showTaskCreateDialog();
}

function showTaskCreateDialog() {
    taskCreateDialogVisible.value = true;
}
</script>

<style scoped>
.UP {
    color: limegreen;
}

.DOWN {
    color: lightcoral;
}
</style>
