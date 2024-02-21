package de.mrsebastian.todoappdemo.backend.graphql;

import de.mrsebastian.todoappdemo.backend.person.rest.PersonDTO;
import de.mrsebastian.todoappdemo.backend.person.service.PersonService;
import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import de.mrsebastian.todoappdemo.backend.task.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final TaskService taskService;

    private final PersonService personService;

    @QueryMapping
    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }

    @QueryMapping
    public List<PersonDTO> getPersons() {
        return personService.getPersonen();
    }
}
