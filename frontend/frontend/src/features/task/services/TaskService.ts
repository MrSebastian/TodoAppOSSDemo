import Task from "@/features/task/types/Task";
import TaskPersisted from "@/features/task/types/TaskPersisted";
import TaskClient from "@/features/task/services/api/TaskClient";
import TaskMapper from "@/features/task/services/api/model/TaskMapper";
import { useSnackbarStore } from "@/stores/snackbar";
import { Levels } from "@/api/error";

export default class TaskService {
    private readonly taskClient = new TaskClient();

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
}
