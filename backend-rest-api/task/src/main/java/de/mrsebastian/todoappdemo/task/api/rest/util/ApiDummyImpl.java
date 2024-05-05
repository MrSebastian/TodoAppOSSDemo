package de.mrsebastian.todoappdemo.task.api.rest.util;

import de.mrsebastian.todoappdemo.task.api.rest.TaskAPI;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskAssigneeDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskCreateDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskUpdateDTO;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ApiDummyImpl implements TaskAPI {
    @Override
    public TaskDTO createTask(TaskCreateDTO newTask) {
        return null;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return List.of();
    }

    @Override
    public void deleteTask(UUID taskId) {

    }

    @Override
    public void updateTask(UUID taskId, TaskUpdateDTO updateTaskData) {

    }

    @Override
    public void updateAssignee(UUID taskId, TaskAssigneeDTO assigneeDTO) {

    }

    @Override
    public void removeAssignee(UUID taskId) {

    }
}
