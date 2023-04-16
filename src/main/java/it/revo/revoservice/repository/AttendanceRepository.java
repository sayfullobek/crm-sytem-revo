package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4000")
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
}
