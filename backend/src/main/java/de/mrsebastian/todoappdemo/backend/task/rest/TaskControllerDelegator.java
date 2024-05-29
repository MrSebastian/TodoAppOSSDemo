package de.mrsebastian.todoappdemo.backend.task.rest;

import de.mrsebastian.todoappdemo.backend.task.service.TaskService;
import de.mrsebastian.todoappdemo.task.api.rest.TaskAPI;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskAssigneeDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskCreateDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskUpdateDTO;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskControllerDelegator implements TaskAPI {

    private final TaskService taskService;

    public TaskDTO createTask(TaskCreateDTO newTask) {
        return taskService.createTask(newTask);
    }

    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }

    public void deleteTask(final UUID taskId) {
        taskService.deleteTask(taskId);
    }

    public void updateTask(final UUID taskId, final TaskUpdateDTO updateTaskData) {
        taskService.updateTask(taskId, updateTaskData);
    }

    public void updateAssignee(final UUID taskId, final TaskAssigneeDTO assigneeDTO) {
        taskService.updateTaskAssignee(taskId, assigneeDTO);
    }

    public void removeAssignee(final UUID taskId) {
        taskService.updateTaskAssignee(taskId, null);
    }

}
