package de.mrsebastian.todoappdemo.backend.task.dataaccess.document;

import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
@Qualifier("TaskDaoDocumentMapper")
public interface TaskDocumentMapper {

    TaskDao toDao(TaskDocument taskDocument);

    TaskDocument toDocument(TaskCreateDao taskCreateDao);
}
