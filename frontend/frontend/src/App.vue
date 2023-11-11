<template>
    <v-app>
        <the-snackbar />
        <v-app-bar
            app
            clipped-left
            dark
            color="primary"
        >
            <v-row align="center">
                <v-col
                    cols="3"
                    class="d-flex align-center justify-start"
                >
                    <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
                    <router-link to="/">
                        <v-toolbar-title class="font-weight-bold">
                            <span class="white--text">TodoApp</span>
                        </v-toolbar-title>
                    </router-link>
                </v-col>
                <v-col
                    cols="6"
                    class="d-flex align-center justify-center"
                >
                    <v-text-field
                        id="suchfeld"
                        v-model="query"
                        flat
                        solo-inverted
                        hide-details
                        label="Suche"
                        clearable
                        prepend-inner-icon="mdi-magnify"
                        color="black"
                        @keyup.enter="search"
                    />
                </v-col>
            </v-row>
        </v-app-bar>
        <v-navigation-drawer
            v-model="drawer"
            app
            clipped
        >
            <v-list>
                <v-list-item :to="{ path: '/getstarted' }">
                    <v-list-item-content>
                        <v-list-item-title>Get started</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-main>
            <v-container fluid>
                <v-fade-transition mode="out-in">
                    <router-view />
                </v-fade-transition>
            </v-container>
        </v-main>
    </v-app>
</template>

<script setup lang="ts">
import { onMounted, ref, watch } from "vue";
import { useRoute } from "vue-router/composables";
import { useSnackbarStore } from "@/stores/snackbar";
import UserService from "@/features/user/services/UserService";

const drawer = ref(true);
const query = ref("");

const route = useRoute();
const snackbarStore = useSnackbarStore();

const userService = new UserService();

onMounted(() => {
    query.value = route.params.query;

    userService.loadUserInfo();
});

watch(
    () => route.params.query,
    (q: string) => {
        if (query.value !== q) {
            query.value = q;
        }
    }
);

//Navigiert zur Seite mit den Suchergebnissen und sendet ein Event zum Auslösen weiterer Suchen.
async function search(): Promise<void> {
    if (query.value !== "" && query.value !== null) {
        snackbarStore.showMessage({
            message: "Sie haben nach " + query.value + " gesucht. ;)",
        });
    }
}
</script>

<style></style>
