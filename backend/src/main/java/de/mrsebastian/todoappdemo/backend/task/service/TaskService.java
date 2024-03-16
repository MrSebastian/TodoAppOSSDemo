package de.mrsebastian.todoappdemo.backend.task.service;

import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.person.service.PersonService;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDataAccessService;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.entity.Task;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskAssigneeDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskCreateDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskUpdateDTO;
import jakarta.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;

    private final TaskDataAccessService taskDAService;

    private final PersonService personService;

    @PreAuthorize("hasAnyRole(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_ADMIN.name())")
    public TaskDTO createTask(TaskCreateDTO newTask) {
        if (newTask.creatorId() != null) {
            personService.existsOrThrow(newTask.creatorId());
        }

        val taskCreateDao = taskMapper.toEntity(newTask);
        return taskMapper.toDTO(taskDAService.createTask(taskCreateDao));
    }

    @PreAuthorize(
        "hasAnyRole(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_ADMIN.name(), T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_READER.name())"
    )
    public List<TaskDTO> getTasks() {
        return taskDAService.getTasks().stream().map(taskMapper::toDTO).toList();
    }

    @PreAuthorize(
        "hasAnyRole(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_ADMIN.name(), T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_READER.name())"
    )
    public List<TaskDTO> getTaskWithAssignee(final UUID assigneeId) {
        return getTasks().stream().filter(task -> assigneeId.equals(task.assigneeId())).toList();
    }

    @PreAuthorize("hasAnyRole(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_ADMIN.name())")
    public void deleteTask(final UUID taskId) {
        checkIfExistsOrThrow(taskId);
        taskDAService.deleteTask(taskId);
    }

    @PreAuthorize("hasAnyRole(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_ADMIN.name())")
    public void updateTask(final UUID taskId, final TaskUpdateDTO updateTask) {
        checkIfExistsOrThrow(taskId);
        taskDAService.updateTask(taskId, taskMapper.toUpdateDao(updateTask));
    }

    @PreAuthorize("hasAnyRole(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_ADMIN.name())")
    public void updateTaskAssignee(final UUID taskId, @Nullable final TaskAssigneeDTO taskAssigneeDTO) {
        checkIfExistsOrThrow(taskId);
        if (taskAssigneeDTO == null) {
            taskDAService.removeAssignee(taskId);
        } else {
            taskDAService.setAssignee(taskId, taskAssigneeDTO.personId());
        }
    }

    private void checkIfExistsOrThrow(final UUID taskId) {
        if (!taskDAService.taskExsits(taskId)) {
            throw new NotFoundException(taskId, Task.class);
        }
    }
}
