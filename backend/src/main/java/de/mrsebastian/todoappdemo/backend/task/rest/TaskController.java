package de.mrsebastian.todoappdemo.backend.task.rest;

import de.mrsebastian.todoappdemo.backend.task.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
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

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") final UUID taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable("id") final UUID taskId, @Valid @RequestBody TaskUpdateDTO updateTaskData) {
        taskService.updateTask(taskId, updateTaskData);
    }

    @PutMapping("{id}/assignee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAssignee(@PathVariable("id") final UUID taskId, @Valid @RequestBody TaskAssigneeDTO assigneeDTO) {
        taskService.updateTaskAssignee(taskId, assigneeDTO);
    }

    @DeleteMapping("{id}/assignee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAssignee(@PathVariable("id") final UUID taskId) {
        taskService.updateTaskAssignee(taskId, null);
    }

}
