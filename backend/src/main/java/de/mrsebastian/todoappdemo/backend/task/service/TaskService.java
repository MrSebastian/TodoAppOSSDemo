package de.mrsebastian.todoappdemo.backend.task.service;

import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.person.service.PersonService;
import de.mrsebastian.todoappdemo.backend.task.domain.Task;
import de.mrsebastian.todoappdemo.backend.task.domain.TaskRepository;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskCreateDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskResposiroty;

    private final TaskMapper taskMapper;

    private final PersonService personService;

    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_CREATE.name())")
    public TaskDTO createTask(TaskCreateDTO newTask) {
        if (newTask.creatorId() != null) {
            personService.existsOrThrow(newTask.creatorId());
        }

        val newEntity = taskMapper.toEntity(newTask);
        return taskMapper.toDTO(taskResposiroty.save(newEntity));
    }

    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_READ.name())")
    public List<TaskDTO> getTasks() {
        return taskResposiroty.findAll().stream().map(taskMapper::toDTO).toList();
    }

    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_DELETE.name())")
    public void deleteTask(final UUID taskId) {
        checkIfExistsOrThrow(taskId);
        taskResposiroty.deleteById(taskId);
    }

    private void checkIfExistsOrThrow(final UUID taskId) {
        if (!taskResposiroty.existsById(taskId)) {
            throw new NotFoundException(taskId, Task.class);
        }
    }
}
