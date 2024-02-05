export default class Task {
    constructor(
        public id: string,
        public title: string,
        public description: string | null,
        public dueDate: string | null
    ) {}
}
