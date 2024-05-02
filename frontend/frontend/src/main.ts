import { createPinia } from "pinia";
import { createApp } from "vue";

import { useErrorHandler } from "@/composables/ErrorHandler";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import router from "./router";

const pinia = createPinia();

const app = createApp(App);

app.use(router);
app.use(pinia);
app.use(vuetify);

const { vueErrorHandler, onUnhandledRejection } = useErrorHandler();
app.config.errorHandler = vueErrorHandler;
window.onunhandledrejection = onUnhandledRejection;

app.mount("#app");
