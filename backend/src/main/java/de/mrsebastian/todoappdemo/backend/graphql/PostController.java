package de.mrsebastian.todoappdemo.backend.graphql;

import de.mrsebastian.todoappdemo.backend.graphql.types.Person;
import de.mrsebastian.todoappdemo.backend.graphql.types.Task;
import de.mrsebastian.todoappdemo.backend.graphql.types.TaskOfPerson;
import de.mrsebastian.todoappdemo.backend.graphql.types.TypeMapper;
import de.mrsebastian.todoappdemo.backend.person.service.PersonService;
import de.mrsebastian.todoappdemo.backend.task.service.TaskService;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final TaskService taskService;

    private final PersonService personService;

    private final TypeMapper typeMapper;

    @QueryMapping
    public List<Task> getTasks() {
        return taskService.getTasks().stream().map(typeMapper::taskDtoToTaskType).toList();
    }

    @QueryMapping
    public List<Person> getPersons() {
        return personService.getPersonen().stream().map(typeMapper::personDtoToPersonType).toList();
    }

    @SchemaMapping
    public List<TaskOfPerson> tasks(Person person) {
        log.atDebug().log("collection tasks of person with id > {}", person.id());

        if (person.id() == null) {
            return Collections.emptyList();
        }

        return taskService.getTaskWithAssignee(person.id()).stream().map(typeMapper::taskDtoToTaskOfPersonType).toList();
    }

    @SchemaMapping
    public Person creator(Task task) {
        if (task.creatorId() != null) {
            return typeMapper.personDtoToPersonType(
                    personService.getPerson(task.creatorId()));
        } else {
            return null;
        }
    }

    @SchemaMapping
    public Person assignee(Task task) {
        if (task.assigneeId() != null) {
            return typeMapper.personDtoToPersonType(
                    personService.getPerson(task.assigneeId()));
        } else {
            return null;
        }
    }
}
