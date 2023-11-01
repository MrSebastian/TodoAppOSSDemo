package de.mrsebastian.todoappdemo.backend.task.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public List<TaskDao> getTasks() {
        return repository.findAll().stream().map(taskDaoMapper::toDao).toList();
    }
}
