export default class PersonPersisted {
    constructor(
        public readonly id: string,
        public email: string,
        public firstname?: string, //TODO possible null from backend
        public lastname?: string //TODO possible null from backend
    ) {}
}
