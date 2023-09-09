import Task from "@/features/task/types/Task";
import TaskCreateDTO from "@/features/task/services/api/model/TaskCreateDTO";
import TaskDTO from "@/features/task/services/api/model/TaskDTO";
import TaskPersisted from "@/features/task/types/TaskPersisted";

export default class TaskMapper {
    taskToWriteDto(task: Task): TaskCreateDTO {
        return new TaskCreateDTO(task.title, task.description, task.dueDate);
    }

    taskDtoToTaskPersisted(dto: TaskDTO): TaskPersisted {
        return new TaskPersisted(
            dto.id,
            dto.title,
            dto.description,
            dto.dueDate
        );
    }
}
