package it.revo.revoservice.utils.groupFull;

import it.revo.revoservice.entity.Group;
import it.revo.revoservice.entity.User;
import it.revo.revoservice.payload.*;
import org.springframework.http.HttpEntity;

import java.util.List;
import java.util.UUID;

public interface GroupLogic {
    ApiResponse addGroup(GroupDto groupDto);

    ApiResponse editGroup(Integer id, GroupDto groupDto);

    ApiResponse deleteGroup(Integer id);

    ResGroup getOneGroup(Integer id);

    List<User> getPupil(Integer id);

    ApiResponse addPupil(Integer id, ReqRegister reqRegister);

    ApiResponse addPupils(Integer id, ReqRegister reqRegister);

    HttpEntity<?> getAttendanceByGroup(Integer id);

    HttpEntity<?> AttendanceByGroup(Integer id, ResAttendanceByGroup pupilId);
}
