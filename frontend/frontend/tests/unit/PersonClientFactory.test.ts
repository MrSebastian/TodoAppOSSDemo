import { describe, expect, it, vi } from "vitest";

import PersonClientLocalStorage from "@/features/person/services/api/impl/localstorage/PersonClientLocalStorage";
import PersonRestClient from "@/features/person/services/api/impl/rest/PersonRestClient";
import PersonClientFactory from "@/features/person/services/api/PersonClientFactory";

const mocks = vi.hoisted(() => {
    return {
        API_BASE: vi.fn(),
    };
});

vi.mock("@/Constants.ts", () => {
    return {
        get API_BASE() {
            return mocks.API_BASE();
        },
    };
});

describe("PersonClientFactory", () => {
    // Returns an instance of PersonRestClient when API_BASE is defined.
    it("should return an instance of PersonRestClient when API_BASE is defined", () => {
        vi.mocked(mocks.API_BASE).mockReturnValue("localhorst");

        // Call the method under test
        const personClient = PersonClientFactory.getPersonClient();

        // Assert that the returned instance is an instance of PersonRestClient
        expect(personClient).toBeInstanceOf(PersonRestClient);
    });

    // API_BASE is null.
    it("should return an instance of PersonClientLocalStorage when API_BASE is undefined", () => {
        vi.mocked(mocks.API_BASE).mockReturnValue(undefined);

        // Call the method under test
        const personClient = PersonClientFactory.getPersonClient();
        // Assert that the returned instance is an instance of PersonClientLocalStorage
        expect(personClient).toBeInstanceOf(PersonClientLocalStorage);
    });

    // API_BASE is null.
    it("should return an instance of PersonClientLocalStorage when API_BASE is empty string", () => {
        vi.mocked(mocks.API_BASE).mockReturnValue("");

        // Call the method under test
        const personClient = PersonClientFactory.getPersonClient();
        // Assert that the returned instance is an instance of PersonClientLocalStorage
        expect(personClient).toBeInstanceOf(PersonClientLocalStorage);
    });
});
