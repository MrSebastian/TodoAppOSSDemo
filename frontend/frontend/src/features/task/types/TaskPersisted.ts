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

    static createDefault(): TaskPersisted {
        return new TaskPersisted("", "", null, null);
    }

    clone(): TaskPersisted {
        return new TaskPersisted(
            this.id,
            this.title,
            this.description,
            this.dueDate
        );
    }
}
