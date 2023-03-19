package it.revo.revoservice.utils.courseFull;

import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.CourseDto;

public interface CourseLogic {
    ApiResponse addCourse(CourseDto courseDto);

    ApiResponse editCourse(Integer id, CourseDto courseDto);

    ApiResponse deleteCourse(Integer id);
}
