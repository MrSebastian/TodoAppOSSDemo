export default class PersonPersisted {
    constructor(
        public readonly id: string,
        public email: string,
        public firstname?: string,
        public lastname?: string
    ) {}
}
