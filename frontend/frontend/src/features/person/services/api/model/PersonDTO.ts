export default class PersonDTO {
    constructor(
        public readonly id: string,
        public readonly email: string,
        public readonly firstname?: string,
        public readonly lastname?: string
    ) {}
}
