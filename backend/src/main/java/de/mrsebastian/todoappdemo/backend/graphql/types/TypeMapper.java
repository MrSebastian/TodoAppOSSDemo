package de.mrsebastian.todoappdemo.backend.graphql.types;

import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TypeMapper {

    Person personDtoToPersonType(PersonDTO personDTO);

    @Mapping(target = "id", source = "taskDTO.id")
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    Task taskDtoToTaskType(TaskDTO taskDTO);

    TaskOfPerson taskDtoToTaskOfPersonType(TaskDTO taskDTO);
}
