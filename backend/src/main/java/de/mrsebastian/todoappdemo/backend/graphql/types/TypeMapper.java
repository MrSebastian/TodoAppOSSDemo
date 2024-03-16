package de.mrsebastian.todoappdemo.backend.graphql.types;

import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TypeMapper {

    Person personDtoToPersonType(PersonDTO personDTO);

    @Mapping(target = "id", source = "taskDTO.id")
    Task taskDtoToTaskType(TaskDTO taskDTO, PersonDTO assignee);

    @Mapping(target = "creatorId", source = "creator.id")
    TaskOfPerson taskDtoToTaskOfPersonType(TaskDTO taskDTO);
}
