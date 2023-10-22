package de.mrsebastian.todoappdemo.backend.graphql;

import de.mrsebastian.todoappdemo.backend.task.rest.TaskDTO;
import de.mrsebastian.todoappdemo.backend.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final TaskService taskService;

    @QueryMapping
    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }
}
