export default class TaskDTO {
    constructor(
        public readonly id: string,
        public readonly title: string,
        public readonly description: string | null,
        public readonly dueDate: string | null,
        public readonly assigneeId: string | null
    ) {}
}
