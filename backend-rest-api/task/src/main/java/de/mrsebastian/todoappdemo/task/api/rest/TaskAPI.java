package de.mrsebastian.todoappdemo.task.api.rest;

import de.mrsebastian.todoappdemo.task.api.rest.model.TaskAssigneeDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskCreateDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskUpdateDTO;
import java.util.List;
import java.util.UUID;

public interface TaskAPI {

    TaskDTO createTask(TaskCreateDTO newTask);

    List<TaskDTO> getTasks();

    void deleteTask(UUID taskId);

    void updateTask(UUID taskId, TaskUpdateDTO updateTaskData);

    void updateAssignee(UUID taskId, TaskAssigneeDTO assigneeDTO);

    void removeAssignee(UUID taskId);
}
