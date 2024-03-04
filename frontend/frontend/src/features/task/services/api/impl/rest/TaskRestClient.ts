import type TaskAssigneeDTO from "@/features/task/services/api/model/TaskAssigneeDTO";
import type TaskUpdateDTO from "@/features/task/services/api/model/TaskUpdateDTO";
import type { TaskClientInterface } from "@/features/task/services/api/TaskClientInterface";

import FetchUtils from "@/api/FetchUtils";
import { API_BACKEND_BASE } from "@/Constants";
import TaskCreateDTO from "@/features/task/services/api/model/TaskCreateDTO";
import TaskDTO from "@/features/task/services/api/model/TaskDTO";

export default class TaskRestClient implements TaskClientInterface {
    private readonly PATH_TASKS = "/tasks";
    private readonly SUBPATH_ASSIGNEE = "/assignee";

    private readonly fetchUtils = new FetchUtils();

    createTask(newTask: TaskCreateDTO): Promise<TaskDTO> {
        return this.fetchUtils.doFetch<TaskDTO>(
            `${API_BACKEND_BASE}${this.PATH_TASKS}`,
            FetchUtils.getPOSTConfig(newTask)
        );
    }

    getTasks(): Promise<TaskDTO[]> {
        return this.fetchUtils.doFetch<TaskDTO[]>(
            `${API_BACKEND_BASE}${this.PATH_TASKS}`,
            FetchUtils.getGETConfig()
        );
    }

    deleteTask(taskId: string): Promise<void> {
        return this.fetchUtils.doFetch<void>(
            `${API_BACKEND_BASE}${this.PATH_TASKS}/${taskId}`,
            FetchUtils.getDeletConfig()
        );
    }

    updateTask(taskId: string, taskUpdateDTO: TaskUpdateDTO): Promise<void> {
        return this.fetchUtils.doFetch<void>(
            `${API_BACKEND_BASE}${this.PATH_TASKS}/${taskId}`,
            FetchUtils.getPUTConfig(taskUpdateDTO)
        );
    }

    setAssignee(
        taskId: string,
        taskAssigneeDTO: TaskAssigneeDTO
    ): Promise<void> {
        return this.fetchUtils.doFetch<void>(
            `${API_BACKEND_BASE}${this.PATH_TASKS}/${taskId}/${this.SUBPATH_ASSIGNEE}`,
            FetchUtils.getPUTConfig(taskAssigneeDTO)
        );
    }

    removeAssignee(taskId: string): Promise<void> {
        return this.fetchUtils.doFetch<void>(
            `${API_BACKEND_BASE}${this.PATH_TASKS}/${taskId}/${this.SUBPATH_ASSIGNEE}`,
            FetchUtils.getDeletConfig()
        );
    }
}
