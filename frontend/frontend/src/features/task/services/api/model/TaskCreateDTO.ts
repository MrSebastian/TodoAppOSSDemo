export default class TaskCreateDTO {
    constructor(
        public readonly title: string,
        public readonly description: string | null,
        public readonly dueDate: string | null
    ) {}
}
