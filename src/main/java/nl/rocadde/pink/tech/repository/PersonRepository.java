package nl.rocadde.pink.tech.repository;

import nl.rocadde.pink.tech.entity.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // Requirement 5
    List<Person> findAll(Sort sort);
}