package de.mrsebastian.todoappdemo.backend.task.rest;

import de.mrsebastian.todoappdemo.backend.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@Valid @RequestBody TaskCreateDTO newTask) {
        return taskService.createTask(newTask);
    }

    @GetMapping
    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }
}
