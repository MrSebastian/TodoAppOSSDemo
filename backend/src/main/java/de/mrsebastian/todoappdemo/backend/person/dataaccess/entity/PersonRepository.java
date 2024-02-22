package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
