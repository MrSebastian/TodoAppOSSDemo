package de.mrsebastian.todoappdemo.backend.task.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskUpdateDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
@Qualifier("TaskDaoDocumentMapper")
public interface TaskDocumentMapper {

    @Mapping(target = "creator", ignore = true)
    TaskDao toDao(TaskDocument taskDocument);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assigneeId", ignore = true)
    TaskDocument toDocument(TaskCreateDao taskCreateDao);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assigneeId", ignore = true)
    void updateDocument(TaskUpdateDao taskUpdateDao, @MappingTarget TaskDocument taskDocument);
}
