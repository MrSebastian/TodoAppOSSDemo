import Task from "@/features/task/types/Task";

export default class TaskPersisted extends Task {
    constructor(
        public id: string,
        title: string,
        description: string | null,
        dueDate: string | null
    ) {
        super(title, description, dueDate);
    }
}
