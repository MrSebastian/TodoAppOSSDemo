export default class TaskUpdateDTO {
    constructor(
        public readonly title: string,
        public readonly description: string,
        public readonly dueDate: string | null
    ) {}
}
