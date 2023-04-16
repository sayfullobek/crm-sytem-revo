package it.revo.revoservice.controller;

import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.GroupDto;
import it.revo.revoservice.payload.ReqRegister;
import it.revo.revoservice.payload.ResAttendanceByGroup;
import it.revo.revoservice.repository.GroupRepository;
import it.revo.revoservice.service.GroupService;
import it.revo.revoservice.utils.groupFull.GroupControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController implements GroupControl {
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    @Override
    @GetMapping
    public HttpEntity<?> getGroup() {
        return ResponseEntity.ok(groupRepository.findAll());
    }

    @Override
    @PostMapping
    public HttpEntity<?> addGroup(GroupDto groupDto) {
        ApiResponse apiResponse = groupService.addGroup(groupDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    @PutMapping("/{id}")
    public HttpEntity<?> editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGroup(@PathVariable Integer id) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public HttpEntity<?> getOneGroup(@PathVariable Integer id) {
        return ResponseEntity.ok(groupService.getOneGroup(id));
    }

    @Override
    @GetMapping("/pupil/{id}")
    public HttpEntity<?> getPupilCourseId(@PathVariable Integer id) {
        return ResponseEntity.ok(groupService.getPupil(id));
    }

    @Override
    @PutMapping("/pupil/{id}")
    public HttpEntity<?> addPupil(@PathVariable Integer id, @RequestBody ReqRegister reqRegister) {
        ApiResponse apiResponse = groupService.addPupil(id, reqRegister);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    @PutMapping("/pupils/{id}")
    public HttpEntity<?> addPupils(@PathVariable Integer id, @RequestBody ReqRegister reqRegister) {
        ApiResponse apiResponse = groupService.addPupils(id, reqRegister);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @Override
    @GetMapping("/getAttendance/{id}")
    public HttpEntity<?> getAttendanceByGroup(@PathVariable Integer id) {
        return groupService.getAttendanceByGroup(id);
    }

    @Override
    @PutMapping("/attendance/{id}")
    public HttpEntity<?> AttendanceByGroup(@PathVariable Integer id, @RequestBody ResAttendanceByGroup pupilId) {
        return groupService.AttendanceByGroup(id, pupilId);
    }
}
