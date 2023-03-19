package it.revo.revoservice.utils.groupFull;

import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.GroupDto;

public interface GroupLogic {
    ApiResponse addGroup(GroupDto groupDto);

    ApiResponse editGroup(Integer id, GroupDto groupDto);

    ApiResponse deleteGroup(Integer id);
}
