package de.mrsebastian.todoappdemo.backend.task.dataaccess;

import java.util.List;
import java.util.UUID;

public interface TaskDataAccessService {

    TaskDao createTask(TaskCreateDao taskCreateDao);

    void deleteTask(UUID taskId);

    boolean taskExsits(UUID taskId);

    void updateTask(UUID taskId, TaskUpdateDao taskUpdateDao);

    List<TaskDao> getTasks();
}
