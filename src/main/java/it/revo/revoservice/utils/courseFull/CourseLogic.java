package it.revo.revoservice.utils.courseFull;

import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.CourseDto;

import java.util.List;

public interface CourseLogic {
    List<CourseDto> getCourse();
    ApiResponse addCourse(CourseDto courseDto);

    ApiResponse editCourse(Integer id, CourseDto courseDto);

    ApiResponse deleteCourse(Integer id);
}
