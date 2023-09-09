export default class Task {
    constructor(
        public title: string,
        public description: string | null,
        public dueDate: string | null
    ) {}

    static createDefault(): Task {
        return new Task("", null, null);
    }
}
