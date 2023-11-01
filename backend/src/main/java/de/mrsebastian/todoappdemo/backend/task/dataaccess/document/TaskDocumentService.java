package de.mrsebastian.todoappdemo.backend.task.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Profile(Profiles.PROFILE_MONGO)
@RequiredArgsConstructor
public class TaskDocumentService implements TaskDataAccessService {

    private final TaskDocumentMapper taskDaoMapper;

    private final TaskDocumentRepository repository;

    @Override
    public TaskDao createTask(TaskCreateDao taskCreateDao) {
        val documentToSave = taskDaoMapper.toDocument(taskCreateDao);
        documentToSave.setId(UUID.randomUUID());
        return taskDaoMapper.toDao(repository.save(documentToSave));
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
