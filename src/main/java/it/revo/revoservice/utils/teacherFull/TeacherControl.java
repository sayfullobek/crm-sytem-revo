package it.revo.revoservice.utils.teacherFull;

import it.revo.revoservice.payload.AccountDto;
import it.revo.revoservice.payload.CourseDto;
import it.revo.revoservice.payload.ReqRegister;
import it.revo.revoservice.payload.WorkProgressDto;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface TeacherControl {
    HttpEntity<?> getPupils();

    HttpEntity<?> getTeachers();

    HttpEntity<?> addAbout(@RequestBody ReqRegister reqRegister);

    HttpEntity<?> editAbout(@PathVariable UUID id, @RequestBody ReqRegister reqRegister);

    HttpEntity<?> deleteAbout(@PathVariable UUID id);

    HttpEntity<?> getOneAbout(@PathVariable UUID id);

    HttpEntity<?> addAccount(@PathVariable UUID id, @RequestBody AccountDto accountDto);

    HttpEntity<?> addWorkProgress(@PathVariable UUID id, @RequestBody WorkProgressDto workProgressDto);
}
