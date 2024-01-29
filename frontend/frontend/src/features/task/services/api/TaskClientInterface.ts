import TaskCreateDTO from "@/features/task/services/api/model/TaskCreateDTO";
import TaskDTO from "@/features/task/services/api/model/TaskDTO";
import TaskUpdateDTO from "@/features/task/services/api/model/TaskUpdateDTO";

export interface TaskClientInterface {
    createTask(newTask: TaskCreateDTO): Promise<TaskDTO>;
    getTasks(): Promise<TaskDTO[]>;
    deleteTask(taskId: string): Promise<void>;
    updateTask(taskId: string, taskUpdateDTO: TaskUpdateDTO): Promise<void>;
}
