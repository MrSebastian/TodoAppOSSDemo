import type PersonCreateDTO from "@/features/person/services/api/model/PersonCreateDTO";
import type { PersonClientInterface } from "@/features/person/services/api/PersonClientInterface";

import FetchUtils from "@/api/FetchUtils";
import { API_BACKEND_BASE } from "@/Constants";
import PersonDTO from "@/features/person/services/api/model/PersonDTO";

export default class PersonRestClient implements PersonClientInterface {
    private readonly PATH_PERSON = "/person";

    private readonly fetchUtils = new FetchUtils();
    createPerson(person: PersonCreateDTO): Promise<PersonDTO> {
        return this.fetchUtils.doFetch<PersonDTO>(
            `${API_BACKEND_BASE}${this.PATH_PERSON}`,
            FetchUtils.getPOSTConfig(person)
        );
    }

    getPersonen(): Promise<PersonDTO[]> {
        return this.fetchUtils.doFetch<PersonDTO[]>(
            `${API_BACKEND_BASE}${this.PATH_PERSON}`,
            FetchUtils.getGETConfig()
        );
    }
}
