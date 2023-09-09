package de.mrsebastian.todoappdemo.backend.task.service;

import de.mrsebastian.todoappdemo.backend.task.domain.Task;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskCreateDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {

    Task toEntity(TaskCreateDTO dto);

    TaskDTO toDTO(Task entity);
}
