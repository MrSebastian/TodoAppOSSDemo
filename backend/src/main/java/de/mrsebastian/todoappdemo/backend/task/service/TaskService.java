package de.mrsebastian.todoappdemo.backend.task.service;

import de.mrsebastian.todoappdemo.backend.task.domain.TaskRepository;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskCreateDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskResposiroty;

    private final TaskMapper taskMapper;


    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_CREATE.name())")
    public TaskDTO createTask(TaskCreateDTO newTask) {
        val newEntity = taskMapper.toEntity(newTask);
        return taskMapper.toDTO(taskResposiroty.save(newEntity));
    }

    @PreAuthorize("hasAuthority(T(de.mrsebastian.todoappdemo.backend.security.AuthoritiesEnum).TASK_READ.name())")
    public List<TaskDTO> getTasks() {
        return taskResposiroty.findAll().stream().map(taskMapper::toDTO).toList();
    }
}
