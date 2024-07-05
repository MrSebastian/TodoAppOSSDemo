import { mount } from "@vue/test-utils";
import { describe, expect, it } from "vitest";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

import BasePersonFields from "@/features/person/components/BasePersonFields.vue";
import Person from "@/features/person/types/Person";

const vuetify = createVuetify({
    components,
    directives,
});

const email = "email@email.com";
const firstname = "firstname";
const lastname = "lastname";
const person = new Person(email, firstname, lastname);

describe("BasePersonFields", () => {
    it("correct data binding", () => {
        const wrapper = mount(BasePersonFields, {
            props: {
                person: person,
            },
            global: {
                plugins: [vuetify],
            },
        });

        expect(
            wrapper.find('[data-testid="email"]').find("input").element.value
        ).toContain(email);
        expect(
            wrapper.find('[data-testid="firstname"]').find("input").element
                .value
        ).toContain(firstname);
        expect(
            wrapper.find('[data-testid="lastname"]').find("input").element.value
        ).toContain(lastname);
    });
});
