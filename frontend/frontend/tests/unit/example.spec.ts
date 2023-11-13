import { shallowMount } from "@vue/test-utils";
import { createVuetify } from "vuetify";
import TheSnackbar from "@/components/TheSnackbar.vue";
import { createPinia } from "pinia";
import { describe, beforeEach, beforeAll, expect, it } from "vitest";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

const pinia = createPinia();

describe("TheSnackbar.vue", () => {
    let vuetify: ReturnType<typeof createVuetify>;

    beforeAll(() => {
        createPinia();
        createVuetify({
            components,
            directives,
        });
    });

    beforeEach(() => {
        vuetify = createVuetify();
    });

    it("renders props.message when passed", () => {
        const message = "Hello_World";
        const wrapper = shallowMount(TheSnackbar, {
            global: {
                plugins: [pinia],
            },
            vuetify,
            props: { message: message },
        });

        expect(wrapper.html()).toContain(message);
    });
});
