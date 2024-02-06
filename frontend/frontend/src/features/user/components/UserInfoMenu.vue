<template>
    <v-menu :close-on-content-click="false">
        <template #activator="{ props }">
            <v-btn
                icon
                v-bind="props"
            >
                <v-icon>mdi-account-circle</v-icon>
            </v-btn>
        </template>

        <v-card>
            <v-card-title>{{ userStore.userState.username }}</v-card-title>
            <v-divider />
            <v-card-text>
                <div>Roles</div>
                <v-list>
                    <v-list-item
                        v-for="role in userStore.userState.roles"
                        :key="role"
                        :title="role"
                    />
                </v-list> </v-card-text
        ></v-card>
    </v-menu>
</template>

<script setup lang="ts">
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
</script>
