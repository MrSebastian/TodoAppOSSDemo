import type { PersonClientInterface } from "@/features/person/services/api/PersonClientInterface";

import { API_BASE } from "@/Constants";
import PersonClientLocalStorage from "@/features/person/services/api/impl/localstorage/PersonClientLocalStorage";
import PersonRestClient from "@/features/person/services/api/impl/rest/PersonRestClient";

export default class PersonClientFactory {
    static getPersonClient(): PersonClientInterface {
        if (API_BASE) {
            return new PersonRestClient();
        } else {
            return new PersonClientLocalStorage();
        }
    }
}
