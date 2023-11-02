package de.mrsebastian.todoappdemo.backend.task.dataaccess.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
