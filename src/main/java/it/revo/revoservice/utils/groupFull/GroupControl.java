package it.revo.revoservice.utils.groupFull;

import it.revo.revoservice.payload.GroupDto;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface GroupControl {
    HttpEntity<?> getGroup();

    HttpEntity<?> addGroup(@RequestBody GroupDto groupDto);

    HttpEntity<?> editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto);

    HttpEntity<?> deleteGroup(@PathVariable Integer id);
}
