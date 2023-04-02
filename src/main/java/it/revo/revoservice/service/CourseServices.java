package it.revo.revoservice.service;

import it.revo.revoservice.entity.Attachment;
import it.revo.revoservice.entity.Course;
import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.CourseDto;
import it.revo.revoservice.repository.AttachmentRepository;
import it.revo.revoservice.repository.CourseRepository;
import it.revo.revoservice.utils.Exception;
import it.revo.revoservice.utils.courseFull.CourseLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServices implements CourseLogic {
    private final CourseRepository courseRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentService attachmentService;

    @Override
    public List<CourseDto> getCourse() {
        List<Course> all = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : all) {
            CourseDto build = CourseDto.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .price(course.getPrice())
                    .expireMonth(course.getExpireMonth())
                    .description(course.getDescription())
                    .build();
            HttpEntity<?> file = attachmentService.getFile(course.getPhotoId());
            build.setFile(file);
            courseDtos.add(build);
        }
        return courseDtos;
    }

    @Override
    public ApiResponse addCourse(CourseDto courseDto) {
        Course build = Course.builder()
                .price(courseDto.getPrice())
                .expireMonth(courseDto.getExpireMonth())
                .description(courseDto.getDescription())
                .build();
        build.setName(courseDto.getName());
        List<Attachment> all = attachmentRepository.findAll();
        Attachment attachment = new Attachment();
        int tr = 1;
        for (int i = 0; i < all.size(); i++) {
            if (tr == all.size()) {
                attachment = all.get(i);
            }
            tr++;
        }
        build.setPhotoId(attachment.getId());
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
