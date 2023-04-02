package it.revo.revoservice.payload;

import it.revo.revoservice.entity.Course;
import it.revo.revoservice.entity.Role;
import it.revo.revoservice.entity.Statistics;
import it.revo.revoservice.entity.User;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAndPupilAndGroupDtoRes {
    private User user;
    private List<GroupDto> groupDtos;
}
