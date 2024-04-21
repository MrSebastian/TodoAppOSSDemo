package de.mrsebastian.todoappdemo.task.api.rest;

import de.mrsebastian.todoappdemo.task.api.rest.model.TaskAssigneeDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskCreateDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskUpdateDTO;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${todoapp.api.task.rest.base-path:/api/tasks}")
@RequiredArgsConstructor
public class TaskAPIController {

    private final TaskAPI restApiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@Valid @RequestBody TaskCreateDTO newTask) {
        return restApiService.createTask(newTask);
    }

    @GetMapping
    public List<TaskDTO> getTasks() {
        return restApiService.getTasks();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") final UUID taskId) {
        restApiService.deleteTask(taskId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable("id") final UUID taskId, @Valid @RequestBody TaskUpdateDTO updateTaskData) {
        restApiService.updateTask(taskId, updateTaskData);
    }

    @PutMapping("{id}/assignee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAssignee(@PathVariable("id") final UUID taskId, @Valid @RequestBody TaskAssigneeDTO assigneeDTO) {
        restApiService.updateAssignee(taskId, assigneeDTO);
    }

    @DeleteMapping("{id}/assignee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAssignee(@PathVariable("id") final UUID taskId) {
        restApiService.removeAssignee(taskId);
    }
}
