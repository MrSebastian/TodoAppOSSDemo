package de.mrsebastian.todoappdemo.backend.person.dataaccess.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
