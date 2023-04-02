package it.revo.revoservice.service;

import it.revo.revoservice.entity.Course;
import it.revo.revoservice.entity.User;
import it.revo.revoservice.entity.enums.RoleName;
import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.ReqRegister;
import it.revo.revoservice.payload.TeacherAndPupilAndGroupDtoRes;
import it.revo.revoservice.repository.CourseRepository;
import it.revo.revoservice.repository.RoleRepository;
import it.revo.revoservice.repository.UserRepository;
import it.revo.revoservice.utils.Exception;
import it.revo.revoservice.utils.teacherFull.TeacherLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherService implements TeacherLogic {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAbout(String api) {
        List<User> users = new ArrayList<>();
        List<User> all = userRepository.findAll();
        for (User user : all) {
            if (api.equals("pupil") && user.getRole().getRoleName().equals(RoleName.PUPIL)) {
                User build = User.builder().firstName(user.getFirstName()).lastName(user.getLastName()).phoneNumber(user.getPhoneNumber()).birthDate(user.getBirthDate()).build();
                build.setId(user.getId());
                users.add(build);
            } else if (api.equals("teacher") && user.getRole().getRoleName().equals(RoleName.TEACHER)) {
                User build = User.builder().firstName(user.getFirstName()).lastName(user.getLastName()).phoneNumber(user.getPhoneNumber()).birthDate(user.getBirthDate()).build();
                build.setId(user.getId());
                users.add(build);
            }
        }
        return users;
    }

    @Override
    public ApiResponse addTeacher(ReqRegister reqRegister) {
        Optional<Course> byId = courseRepository.findById(reqRegister.getCourseId());
        if (byId.isPresent()) {
            User user = new User(
                    reqRegister.getFirstName(),
                    reqRegister.getLastName(),
                    reqRegister.getPhoneNumber(),
                    roleRepository.findById(reqRegister.getApi().equals("pupil") ? 3 : 2).orElseThrow(() -> new ResourceNotFoundException("getRole")),
                    Collections.singletonList(courseRepository.findById(reqRegister.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("getCourse"))),
                    reqRegister.getBirthDate()
            );
            if (reqRegister.getApi().equals("teacher")) {
                user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
                user.setCode(reqRegister.getPassword());
            }
            userRepository.save(user);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public ApiResponse editTeacher(UUID id, ReqRegister reqRegister) {
        return null;
    }

    @Override
    public ApiResponse deleteTeacher(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            userRepository.deleteById(id);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public TeacherAndPupilAndGroupDtoRes getOne(UUID id) {
        try {
            Optional<User> byId = userRepository.findById(id);
            if (byId.isPresent()) {
                User user = byId.get();
                return TeacherAndPupilAndGroupDtoRes.builder().user(user).build();
            }
            return null;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
