import type TaskCreateDTO from "@/features/task/services/api/model/TaskCreateDTO";
import type TaskUpdateDTO from "@/features/task/services/api/model/TaskUpdateDTO";

import { v4 as uuidv4 } from "uuid";

import Task from "@/features/task/services/api/impl/localstorage/Task";
import TaskDTO from "@/features/task/services/api/model/TaskDTO";

export default class TaskMapper {
    toTask(createDTO: TaskCreateDTO): Task {
        return new Task(
            uuidv4().toString(),
            createDTO.title,
            createDTO.description,
            createDTO.dueDate
        );
    }

    toTaskDTO(task: Task): TaskDTO {
        return new TaskDTO(task.id, task.title, task.description, task.dueDate);
    }

    updateTask(taskToUpdate: Task, updateData: TaskUpdateDTO) {
        taskToUpdate.dueDate = updateData.dueDate;
        taskToUpdate.title = updateData.title;
        taskToUpdate.description = updateData.description;
    }
}
