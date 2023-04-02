package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4000")
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
