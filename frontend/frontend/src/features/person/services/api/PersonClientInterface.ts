import type PersonCreateDTO from "@/features/person/services/api/model/PersonCreateDTO";
import type PersonDTO from "@/features/person/services/api/model/PersonDTO";

export interface PersonClientInterface {
    getPersonen(): Promise<PersonDTO[]>;

    createPerson(person: PersonCreateDTO): Promise<PersonDTO>;

    deletePerson(id: string): Promise<void>;
}
