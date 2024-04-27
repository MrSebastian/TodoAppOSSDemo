import type PersonCreateDTO from "@/features/person/services/api/model/PersonCreateDTO";
import type PersonDTO from "@/features/person/services/api/model/PersonDTO";
import type PersonUpdateDTO from "@/features/person/services/api/model/PersonUpdateDTO";

export interface PersonClientInterface {
    getPersonen(): Promise<PersonDTO[]>;

    getPerson(id: string): Promise<PersonDTO>;

    createPerson(person: PersonCreateDTO): Promise<PersonDTO>;

    deletePerson(id: string): Promise<void>;

    updatePerson(id: string, person: PersonUpdateDTO): Promise<void>;
}
