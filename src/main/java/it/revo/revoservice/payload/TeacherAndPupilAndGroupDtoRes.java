package it.revo.revoservice.payload;

import it.revo.revoservice.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAndPupilAndGroupDtoRes {
    private User user;
    private List<GroupDto> groupDtos;
}
