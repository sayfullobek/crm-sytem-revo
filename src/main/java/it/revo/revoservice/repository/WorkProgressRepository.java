package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Accounts;
import it.revo.revoservice.entity.WorkProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4000")
public interface WorkProgressRepository extends JpaRepository<WorkProgress, Integer> {
}
