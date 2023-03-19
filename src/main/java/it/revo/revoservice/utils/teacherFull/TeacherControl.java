package it.revo.revoservice.utils.teacherFull;

import it.revo.revoservice.payload.CourseDto;
import it.revo.revoservice.payload.ReqRegister;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TeacherControl {
    HttpEntity<?> getPupils();

    HttpEntity<?> getTeachers();

    HttpEntity<?> addAbout(@RequestBody ReqRegister reqRegister);

    HttpEntity<?> editAbout(@PathVariable Integer id, @RequestBody ReqRegister reqRegister);

    HttpEntity<?> deleteAbout(@PathVariable Integer id);
}
