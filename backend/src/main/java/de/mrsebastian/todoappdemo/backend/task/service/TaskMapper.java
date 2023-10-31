package de.mrsebastian.todoappdemo.backend.task.service;

import de.mrsebastian.todoappdemo.backend.person.domain.Person;
import de.mrsebastian.todoappdemo.backend.task.domain.Task;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskCreateDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface TaskMapper {

    @Mapping(target = "creator", source = "creatorId")
    Task toEntity(TaskCreateDTO dto);

    TaskDTO toDTO(Task entity);

    default Person createPersonLink(UUID id) {
        return id == null ? null : Person.builder().id(id).build();
    }
}
