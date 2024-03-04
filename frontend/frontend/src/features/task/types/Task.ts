export default class Task {
    constructor(
        public title: string,
        public description: string | null,
        public dueDate: string | null,
        public assigneeId: string | null
    ) {}

    static createDefault(): Task {
        return new Task("", null, null, null);
    }
}
