import type { PersonClientInterface } from "@/features/person/services/api/PersonClientInterface";

import { DONT_USE_RESTCLIENT } from "@/Constants";
import PersonClientLocalStorage from "@/features/person/services/api/impl/localstorage/PersonClientLocalStorage";
import PersonRestClient from "@/features/person/services/api/impl/rest/PersonRestClient";

export default class PersonClientFactory {
    static getPersonClient(): PersonClientInterface {
        if (DONT_USE_RESTCLIENT) {
            return new PersonClientLocalStorage();
        } else {
            return new PersonRestClient();
        }
    }
}
