import type Person from "@/features/person/services/api/impl/localstorage/Person";
import type PersonCreateDTO from "@/features/person/services/api/model/PersonCreateDTO";
import type { PersonClientInterface } from "@/features/person/services/api/PersonClientInterface";

import PersonMapper from "@/features/person/services/api/impl/localstorage/PersonMapper";
import PersonDTO from "@/features/person/services/api/model/PersonDTO";

export default class PersonClientLocalStorage implements PersonClientInterface {
    private readonly KEY_PERSON_ARRAY = "persons";

    private readonly personMapper = new PersonMapper();

    createPerson(person: PersonCreateDTO): Promise<PersonDTO> {
        const newLocalPerson =
            this.personMapper.personCreateDTOToPerson(person);
        this.savePerson(newLocalPerson);
        return Promise.resolve(
            this.personMapper.personToPersonDTO(newLocalPerson)
        );
    }

    getPersonen(): Promise<PersonDTO[]> {
        return Promise.resolve(
            this.getOrCreatePersonsOfLocalStorage().map((person) =>
                this.personMapper.personToPersonDTO(person)
            )
        );
    }

    deletePerson(id: string): Promise<void> {
        const personsOfLocalStorage = this.getOrCreatePersonsOfLocalStorage();
        const indexOfPersonToDelete = personsOfLocalStorage.findIndex(
            (person) => person.id === id
        );
        if (indexOfPersonToDelete !== -1) {
            personsOfLocalStorage.splice(indexOfPersonToDelete);
            this.saveAllPersons(personsOfLocalStorage);
        }

        return Promise.resolve();
    }

    private getOrCreatePersonsOfLocalStorage(): Array<Person> {
        let localStoragePersonsAsString = localStorage.getItem(
            this.KEY_PERSON_ARRAY
        );

        if (localStoragePersonsAsString === null) {
            localStoragePersonsAsString = JSON.stringify([]);
            localStorage.setItem(
                this.KEY_PERSON_ARRAY,
                localStoragePersonsAsString
            );
        }

        return JSON.parse(localStoragePersonsAsString);
    }

    private savePerson(person: Person): void {
        const localPersons = this.getOrCreatePersonsOfLocalStorage();
        localPersons.push(person);
        this.saveAllPersons(localPersons);
    }

    private saveAllPersons(persons: Person[]): void {
        localStorage.setItem(this.KEY_PERSON_ARRAY, JSON.stringify(persons));
    }
}
