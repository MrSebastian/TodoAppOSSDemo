import type Person from "@/features/person/types/Person";
import type PersonPersisted from "@/features/person/types/PersonPersisted";

import PersonMapper from "@/features/person/services/api/model/PersonMapper";
import PersonClientFactory from "@/features/person/services/api/PersonClientFactory";

export default class PersonService {
    private readonly personClient = PersonClientFactory.getPersonClient();

    private readonly personMapper = new PersonMapper();

    createPerson(person: Person): Promise<PersonPersisted> {
        const personDto = this.personMapper.personToCreateDto(person);
        return this.personClient
            .createPerson(personDto)
            .then((personDto) =>
                this.personMapper.personDtoToPersonPersisted(personDto)
            );
    }

    getPersonen(): Promise<Array<PersonPersisted>> {
        return this.personClient
            .getPersonen()
            .then((personen) =>
                personen.map((person) =>
                    this.personMapper.personDtoToPersonPersisted(person)
                )
            );
    }

    getPerson(personId: string): Promise<PersonPersisted> {
        return this.personClient
            .getPerson(personId)
            .then((person) =>
                this.personMapper.personDtoToPersonPersisted(person)
            );
    }

    deletePerson(personId: string): Promise<void> {
        return this.personClient.deletePerson(personId);
    }

    updatePerson(updatedPerson: PersonPersisted): Promise<void> {
        const body =
            this.personMapper.persistedPersonToPersonUpdateDTO(updatedPerson);
        return this.personClient.updatePerson(updatedPerson.id, body);
    }
}
