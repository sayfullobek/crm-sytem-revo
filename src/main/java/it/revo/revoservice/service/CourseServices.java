package it.revo.revoservice.service;

import it.revo.revoservice.entity.Course;
import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.CourseDto;
import it.revo.revoservice.repository.CourseRepository;
import it.revo.revoservice.utils.Exception;
import it.revo.revoservice.utils.courseFull.CourseLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServices implements CourseLogic {
    private final CourseRepository courseRepository;

    @Override
    public ApiResponse addCourse(CourseDto courseDto) {
        Course build = Course.builder()
                .price(courseDto.getPrice())
                .expireMonth(courseDto.getExpireMonth())
                .build();
        build.setName(courseDto.getName());
        courseRepository.save(build);
        return new ApiResponse(Exception.SUCCESS, true);
    }

    @Override
    public ApiResponse editCourse(Integer id, CourseDto courseDto) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            Course course = byId.get();
            course.setName(courseDto.getName());
            course.setPrice(courseDto.getPrice());
            course.setExpireMonth(courseDto.getExpireMonth());
            courseRepository.save(course);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public ApiResponse deleteCourse(Integer id) {
        Optional<Course> byId = courseRepository.findById(id);
        if (byId.isPresent()) {
            Course course = byId.get();
            courseRepository.delete(course);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }
}
