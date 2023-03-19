package it.revo.revoservice.utils.courseFull;

import it.revo.revoservice.payload.CourseDto;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface CourseControl {
    HttpEntity<?> getCourse();

    HttpEntity<?> addCourse(@RequestBody CourseDto courseDto);

    HttpEntity<?> editCourse(@PathVariable Integer id, @RequestBody CourseDto courseDto);

    HttpEntity<?> deleteCourse(@PathVariable Integer id);
}
