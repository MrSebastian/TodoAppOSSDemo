package de.mrsebastian.todoappdemo.backend.task.dataaccess.entity;

import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskUpdateDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
@Qualifier("TaskDaoEntityMapper")
public interface TaskEntityMapper {

    TaskDao toDao(Task taskEntity);

    Task toEntity(TaskCreateDao taskCreateDao);

    void updateEntity(TaskUpdateDao taskUpdateDao, @MappingTarget Task taskEntity);
}
