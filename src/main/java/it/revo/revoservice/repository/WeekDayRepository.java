package it.revo.revoservice.repository;

import it.revo.revoservice.entity.WeekDays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekDayRepository extends JpaRepository<WeekDays, Integer> {
}
