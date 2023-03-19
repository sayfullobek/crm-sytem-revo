package it.revo.revoservice.utils.teacherFull;

import it.revo.revoservice.entity.User;
import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.ReqRegister;

import java.util.List;

public interface TeacherLogic {
    List<User> getAbout(String api);

    ApiResponse addTeacher(ReqRegister reqRegister);

    ApiResponse editTeacher(Integer id, ReqRegister reqRegister);

    ApiResponse deleteTeacher(Integer id);
}
