package de.mrsebastian.todoappdemo.backend.task.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.configuration.Profiles;
import de.mrsebastian.todoappdemo.backend.exception.NotFoundException;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDataAccessService;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskUpdateDao;
import jakarta.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
    public void updateTask(UUID taskId, TaskUpdateDao taskUpdateDao) {
        val task = getTaskOrThrow(taskId);
        taskDaoMapper.updateDocument(taskUpdateDao, task);
        repository.save(task);
    }

    @Override
    public List<TaskDao> getTasks() {
        return repository.findAll().stream().map(taskDaoMapper::toDao).toList();
    }

    @Override
    public void removeAssignee(UUID taskId) {
        updateDateAssigneeRefOfReferencedTask(taskId, null);
    }

    @Override
    public void setAssignee(UUID taskId, UUID personId) {
        updateDateAssigneeRefOfReferencedTask(taskId, personId);
    }

    private TaskDocument getTaskOrThrow(final UUID taskId) {
        return repository.findById(taskId).orElseThrow(() -> new NotFoundException(taskId, TaskDocument.class));
    }

    private void updateDateAssigneeRefOfReferencedTask(final UUID taskId, @Nullable final UUID assigneeRef) {
        val task = getTaskOrThrow(taskId);
        task.setAssigneeId(assigneeRef);
        repository.save(task);
    }
}
