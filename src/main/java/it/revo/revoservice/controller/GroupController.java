package it.revo.revoservice.controller;

import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.GroupDto;
import it.revo.revoservice.repository.GroupRepository;
import it.revo.revoservice.service.GroupService;
import it.revo.revoservice.utils.groupFull.GroupControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public HttpEntity<?> editGroup(Integer id, GroupDto groupDto) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGroup(Integer id) {
        return null;
    }
}
