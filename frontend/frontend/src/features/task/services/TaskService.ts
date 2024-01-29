import { Levels } from "@/api/error";
import TaskMapper from "@/features/task/services/api/model/TaskMapper";
import TaskClientFactory from "@/features/task/services/api/TaskClientFactory";
import Task from "@/features/task/types/Task";
import TaskPersisted from "@/features/task/types/TaskPersisted";
import { useSnackbarStore } from "@/stores/snackbar";

export default class TaskService {
    private readonly taskClient = TaskClientFactory.getTaskClient();

    private readonly taskMapper = new TaskMapper();

    private readonly snackbarStore = useSnackbarStore();
    createTask(newTask: Task): Promise<TaskPersisted> {
        const body = this.taskMapper.taskToWriteDto(newTask);
        return this.taskClient
            .createTask(body)
            .then((dto) => this.taskMapper.taskDtoToTaskPersisted(dto))
            .then((task) => {
                this.snackbarStore.showMessage({
                    message: "Task erstellt",
                    level: Levels.INFO,
                });
                return task;
            });
    }

    getTasks(): Promise<TaskPersisted[]> {
        return this.taskClient
            .getTasks()
            .then((dtos) =>
                dtos.map((dto) => this.taskMapper.taskDtoToTaskPersisted(dto))
            );
    }

    deleteTask(taskId: string): Promise<void> {
        return this.taskClient.deleteTask(taskId).then(() =>
            this.snackbarStore.showMessage({
                message: "Task gelöscht",
                level: Levels.INFO,
            })
        );
    }

    updateTask(updatedTask: TaskPersisted): Promise<void> {
        const body = this.taskMapper.persistedTaskToTaskUpdateDTO(updatedTask);
        return this.taskClient.updateTask(updatedTask.id, body).then(() => {
            this.snackbarStore.showMessage({
                message: "Task aktualisiert",
                level: Levels.INFO,
            });
        });
    }
}
