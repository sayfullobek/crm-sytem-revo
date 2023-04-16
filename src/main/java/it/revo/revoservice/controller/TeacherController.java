package it.revo.revoservice.controller;

import it.revo.revoservice.payload.AccountDto;
import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.ReqRegister;
import it.revo.revoservice.payload.WorkProgressDto;
import it.revo.revoservice.repository.UserRepository;
import it.revo.revoservice.service.TeacherService;
import it.revo.revoservice.utils.teacherFull.TeacherControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public HttpEntity<?> editAbout(UUID id, ReqRegister reqRegister) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAbout(UUID id) {
        ApiResponse apiResponse = teacherService.deleteTeacher(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getOneAbout(UUID id) {
        return ResponseEntity.ok(teacherService.getOne(id));
    }

    @Override
    @PutMapping("/account/{id}")
    public HttpEntity<?> addAccount(UUID id, AccountDto accountDto) {
        ApiResponse apiResponse = teacherService.addAccount(id, accountDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    @PutMapping("/workProgress/{id}")
    public HttpEntity<?> addWorkProgress(UUID id, WorkProgressDto workProgressDto) {
        ApiResponse apiResponse = teacherService.addWorkProgress(id, workProgressDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}