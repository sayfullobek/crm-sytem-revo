package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Attendance;
import it.revo.revoservice.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4000")
public interface StatisticsRepository extends JpaRepository<Statistics, UUID> {
}
