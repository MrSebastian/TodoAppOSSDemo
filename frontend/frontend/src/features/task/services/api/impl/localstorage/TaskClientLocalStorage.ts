import type { TaskClientInterface } from "@/features/task/services/api/TaskClientInterface";

import Task from "@/features/task/services/api/impl/localstorage/Task";
import TaskMapper from "@/features/task/services/api/impl/localstorage/TaskMapper";
import TaskCreateDTO from "@/features/task/services/api/model/TaskCreateDTO";
import TaskDTO from "@/features/task/services/api/model/TaskDTO";
import TaskUpdateDTO from "@/features/task/services/api/model/TaskUpdateDTO";

export default class TaskClientLocalStorage implements TaskClientInterface {
    private readonly KEY_TASK_ARRAY = "tasks";

    private readonly mapper = new TaskMapper();

    createTask(newTask: TaskCreateDTO): Promise<TaskDTO> {
        const newLocalTask = this.mapper.toTask(newTask);
        this.saveTask(newLocalTask);
        return Promise.resolve(this.mapper.toTaskDTO(newLocalTask));
    }

    getTasks(): Promise<TaskDTO[]> {
        return Promise.resolve(
            this.getOrCreateTasksOfLocalStorage().map((task) =>
                this.mapper.toTaskDTO(task)
            )
        );
    }

    deleteTask(taskId: string): Promise<void> {
        const storedTasks = this.getOrCreateTasksOfLocalStorage();
        const indexOfItemToDelete = storedTasks.findIndex(
            (task) => task.id === taskId
        );
        if (indexOfItemToDelete > -1) {
            storedTasks.splice(indexOfItemToDelete, 1);
        }

        return Promise.resolve();
    }

    updateTask(taskId: string, taskUpdateDTO: TaskUpdateDTO): Promise<void> {
        const taskToUpdate = this.getTask(taskId);
        if (!!taskToUpdate) {
            this.mapper.updateTask(taskToUpdate, taskUpdateDTO);
            this.saveTask(taskToUpdate);
        }

        return Promise.resolve();
    }

    private saveTask(task: Task): void {
        const currentStoredTasks = this.getOrCreateTasksOfLocalStorage();
        const indexOfTaskWithId = currentStoredTasks.findIndex(
            (storedTask) => storedTask.id === task.id
        );
        if (indexOfTaskWithId > -1) {
            currentStoredTasks[indexOfTaskWithId] = task;
        } else {
            currentStoredTasks.push(task);
        }
        localStorage.setItem(
            this.KEY_TASK_ARRAY,
            JSON.stringify(currentStoredTasks)
        );
    }

    private getTask(taskId: String): Task | undefined {
        const storedTasks = this.getOrCreateTasksOfLocalStorage();
        return storedTasks.find((task) => task.id === taskId);
    }

    private getOrCreateTasksOfLocalStorage(): Array<Task> {
        let localStorageTaskAsString = localStorage.getItem(
            this.KEY_TASK_ARRAY
        );
        if (localStorageTaskAsString === null) {
            localStorageTaskAsString = JSON.stringify([]);
            localStorage.setItem(this.KEY_TASK_ARRAY, localStorageTaskAsString);
        }

        return JSON.parse(localStorageTaskAsString);
    }
}
