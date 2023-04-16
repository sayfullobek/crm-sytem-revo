package it.revo.revoservice.utils.groupFull;

import it.revo.revoservice.payload.GroupDto;
import it.revo.revoservice.payload.ReqRegister;
import it.revo.revoservice.payload.ResAttendanceByGroup;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GroupControl {
    HttpEntity<?> getGroup();

    HttpEntity<?> addGroup(@RequestBody GroupDto groupDto);

    HttpEntity<?> editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto);

    HttpEntity<?> deleteGroup(@PathVariable Integer id);

    HttpEntity<?> getOneGroup(@PathVariable Integer id);

    HttpEntity<?> getPupilCourseId(@PathVariable Integer id);

    HttpEntity<?> addPupil(@PathVariable Integer id, @RequestBody ReqRegister reqRegister);

    HttpEntity<?> addPupils(@PathVariable Integer id, @RequestBody ReqRegister reqRegister);

    HttpEntity<?> getAttendanceByGroup(@PathVariable Integer id);

    HttpEntity<?> AttendanceByGroup(@PathVariable Integer id, @RequestBody ResAttendanceByGroup pupilId);
}
