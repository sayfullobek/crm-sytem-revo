package it.revo.revoservice.repository;

import it.revo.revoservice.entity.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4000")
public interface WeekDayRepository extends JpaRepository<WeekDays, Integer> {
}
