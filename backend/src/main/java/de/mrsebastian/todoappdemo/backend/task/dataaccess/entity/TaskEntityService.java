package de.mrsebastian.todoappdemo.backend.task.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDataAccessService;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskUpdateDao;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({ Profiles.PROFILE_SQL })
@RequiredArgsConstructor
public class TaskEntityService implements TaskDataAccessService {

    private final TaskEntityMapper taskDaoMapper;

    private final TaskRepository repository;

    @Override
    public TaskDao createTask(TaskCreateDao taskCreateDao) {
        return taskDaoMapper.toDao(repository.save(taskDaoMapper.toEntity(taskCreateDao)));
    }

    @Override
    public void deleteTask(UUID taskId) {
        repository.deleteById(taskId);
    }

    @Override
    public boolean taskExsits(UUID taskId) {
        return repository.existsById(taskId);
    }

    @Override
    public void updateTask(UUID taskId, TaskUpdateDao taskUpdateDao) {
        val task = repository.findById(taskId).orElseThrow(() -> new NotFoundException(taskId, Task.class));
        taskDaoMapper.updateEntity(taskUpdateDao, task);
        repository.save(task);
    }

    @Override
    public List<TaskDao> getTasks() {
        return repository.findAll().stream().map(taskDaoMapper::toDao).toList();
    }
}
