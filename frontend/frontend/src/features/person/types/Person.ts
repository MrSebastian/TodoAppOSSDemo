export default class Person {
    constructor(
        public email: string,
        public firstname?: string,
        public lastname?: string
    ) {}

    static createDefault(): Person {
        return new Person("", undefined, undefined);
    }
}
