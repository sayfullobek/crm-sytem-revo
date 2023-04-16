package it.revo.revoservice.utils.teacherFull;

import it.revo.revoservice.entity.User;
import it.revo.revoservice.payload.*;

import java.util.List;
import java.util.UUID;

public interface TeacherLogic {
    List<User> getAbout(String api);

    ApiResponse addTeacher(ReqRegister reqRegister);

    ApiResponse editTeacher(UUID id, ReqRegister reqRegister);

    ApiResponse deleteTeacher(UUID id);

    TeacherAndPupilAndGroupDtoRes getOne(UUID id);

    ApiResponse addAccount(UUID id, AccountDto accountDto);

    ApiResponse addWorkProgress(UUID id, WorkProgressDto workProgressDto);
}
