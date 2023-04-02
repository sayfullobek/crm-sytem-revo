package it.revo.revoservice.utils.teacherFull;

import it.revo.revoservice.entity.User;
import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.ReqRegister;
import it.revo.revoservice.payload.TeacherAndPupilAndGroupDtoRes;

import java.util.List;
import java.util.UUID;

public interface TeacherLogic {
    List<User> getAbout(String api);

    ApiResponse addTeacher(ReqRegister reqRegister);

    ApiResponse editTeacher(UUID id, ReqRegister reqRegister);

    ApiResponse deleteTeacher(UUID id);

    TeacherAndPupilAndGroupDtoRes getOne(UUID id);
}
