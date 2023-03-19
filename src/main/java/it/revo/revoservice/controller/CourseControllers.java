package it.revo.revoservice.controller;

import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.CourseDto;
import it.revo.revoservice.repository.CourseRepository;
import it.revo.revoservice.service.CourseServices;
import it.revo.revoservice.utils.courseFull.CourseControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseControllers implements CourseControl {
    private final CourseRepository courseRepository;
    private final CourseServices courseService;

    @Override
    @GetMapping
    public HttpEntity<?> getCourse() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @Override
    @PostMapping
    public HttpEntity<?> addCourse(@RequestBody CourseDto courseDto) {
        ApiResponse apiResponse = courseService.addCourse(courseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editCourse(@PathVariable Integer id, @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.editCourse(id, courseDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCourse(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
