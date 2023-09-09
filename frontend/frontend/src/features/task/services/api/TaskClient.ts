import TaskCreateDTO from "@/features/task/services/api/model/TaskCreateDTO";
import TaskDTO from "@/features/task/services/api/model/TaskDTO";
import FetchUtils from "@/api/FetchUtils";
import { API_BACKEND_BASE } from "@/Constants";

export default class TaskClient {
    private readonly fetchUtils = new FetchUtils();

    createTask(newTask: TaskCreateDTO): Promise<TaskDTO> {
        return this.fetchUtils.doFetch<TaskDTO>(
            `${API_BACKEND_BASE}/tasks`,
            FetchUtils.getPOSTConfig(newTask)
        );
    }
}
