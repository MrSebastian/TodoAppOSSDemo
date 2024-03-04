export default class PersonCreateDTO {
    constructor(
        public readonly email: string,
        public readonly firstname?: string,
        public readonly lastname?: string
    ) {}
}
