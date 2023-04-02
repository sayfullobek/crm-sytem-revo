package it.revo.revoservice.repository;

import it.revo.revoservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4000")
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
