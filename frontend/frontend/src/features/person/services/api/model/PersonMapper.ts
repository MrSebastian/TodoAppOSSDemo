import type PersonDTO from "@/features/person/services/api/model/PersonDTO";
import type Person from "@/features/person/types/Person";

import PersonCreateDTO from "@/features/person/services/api/model/PersonCreateDTO";
import PersonUpdateDTO from "@/features/person/services/api/model/PersonUpdateDTO";
import PersonPersisted from "@/features/person/types/PersonPersisted";

export default class PersonMapper {
    personToCreateDto(person: Person): PersonCreateDTO {
        return new PersonCreateDTO(
            person.email,
            person.firstname,
            person.lastname
        );
    }

    personDtoToPersonPersisted(person: PersonDTO): PersonPersisted {
        return new PersonPersisted(
            person.id,
            person.email,
            person.firstname,
            person.lastname
        );
    }

    persistedPersonToPersonUpdateDTO(person: PersonPersisted): PersonUpdateDTO {
        return new PersonUpdateDTO(
            person.email,
            person.firstname,
            person.lastname
        );
    }
}
