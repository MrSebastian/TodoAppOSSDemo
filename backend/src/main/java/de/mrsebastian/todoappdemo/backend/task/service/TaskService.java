package de.mrsebastian.todoappdemo.backend.task.service;

import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.person.service.PersonService;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDataAccessService;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.entity.Task;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskCreateDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;

    private final TaskDataAccessService taskDAService;

    private final PersonService personService;

    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_CREATE.name())")
    public TaskDTO createTask(TaskCreateDTO newTask) {
        if (newTask.creatorId() != null) {
            personService.existsOrThrow(newTask.creatorId());
        }

        val taskCreateDao = taskMapper.toEntity(newTask);
        return taskMapper.toDTO(taskDAService.createTask(taskCreateDao));
    }

    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_READ.name())")
    public List<TaskDTO> getTasks() {
        return taskDAService.getTasks().stream().map(taskMapper::toDTO).toList();
    }

    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_DELETE.name())")
    public void deleteTask(final UUID taskId) {
        checkIfExistsOrThrow(taskId);
        taskDAService.deleteTask(taskId);
    }

    private void checkIfExistsOrThrow(final UUID taskId) {
        if (!taskDAService.taskExsits(taskId)) {
            throw new NotFoundException(taskId, Task.class);
        }
    }
}
