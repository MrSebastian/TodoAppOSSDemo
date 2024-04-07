export default class PersonPersisted {
    constructor(
        public readonly id: string,
        public email: string,
        public firstname?: string, //TODO possible null from backend
        public lastname?: string //TODO possible null from backend
    ) {}

    clone(): PersonPersisted {
        return new PersonPersisted(
            this.id,
            this.email,
            this.firstname,
            this.lastname
        );
    }

    static createDefault(): PersonPersisted {
        return new PersonPersisted("", "", undefined, undefined);
    }
}
