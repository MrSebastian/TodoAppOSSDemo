package de.mrsebastian.todoappdemo.backend.task.service;

import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskCreateDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskDao;
import de.mrsebastian.todoappdemo.backend.task.dataaccess.TaskUpdateDao;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskCreateDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskUpdateDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {

    TaskCreateDao toEntity(TaskCreateDTO dto);

    TaskUpdateDao toUpdateDao(TaskUpdateDTO dto);

    TaskDTO toDTO(TaskDao taskDao);
}
