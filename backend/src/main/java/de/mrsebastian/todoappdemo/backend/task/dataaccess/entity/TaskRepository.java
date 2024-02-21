package de.mrsebastian.todoappdemo.backend.task.dataaccess.entity;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
