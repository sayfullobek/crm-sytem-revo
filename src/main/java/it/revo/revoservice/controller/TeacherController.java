package it.revo.revoservice.controller;

import it.revo.revoservice.payload.ReqRegister;
import it.revo.revoservice.repository.UserRepository;
import it.revo.revoservice.service.TeacherService;
import it.revo.revoservice.utils.teacherFull.TeacherControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacherAndPupil")
@RequiredArgsConstructor
public class TeacherController implements TeacherControl {
    private final UserRepository userRepository;
    private final TeacherService teacherService;

    @Override
    @GetMapping("/getPupils")
    public HttpEntity<?> getPupils() {
        return ResponseEntity.ok(teacherService.getAbout("pupil"));
    }

    @Override
    @GetMapping("/getTeachers")
    public HttpEntity<?> getTeachers() {
        return ResponseEntity.ok(teacherService.getAbout("teacher"));
    }

    @Override
    @PostMapping
    public HttpEntity<?> addAbout(ReqRegister reqRegister) {
        return ResponseEntity.ok(teacherService.addTeacher(reqRegister));
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editAbout(Integer id, ReqRegister reqRegister) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAbout(Integer id) {
        return null;
    }
}