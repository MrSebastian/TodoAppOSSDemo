import type PersonCreateDTO from "@/features/person/services/api/model/PersonCreateDTO";
import type PersonUpdateDTO from "@/features/person/services/api/model/PersonUpdateDTO";

import { v4 as uuidv4 } from "uuid";

import Person from "@/features/person/services/api/impl/localstorage/Person";
import PersonDTO from "@/features/person/services/api/model/PersonDTO";

export default class PersonMapper {
    personToPersonDTO(person: Person): PersonDTO {
        return new PersonDTO(
            person.id,
            person.email,
            person.firstname,
            person.lastname
        );
    }

    personCreateDTOToPerson(person: PersonCreateDTO): Person {
        return new Person(
            uuidv4().toString(),
            person.email,
            person.firstname,
            person.lastname
        );
    }

    updatePerson(personToUpdate: Person, updateData: PersonUpdateDTO): void {
        personToUpdate.email = updateData.email;
        personToUpdate.firstname = updateData.firstname;
        personToUpdate.lastname = updateData.lastname;
    }
}
