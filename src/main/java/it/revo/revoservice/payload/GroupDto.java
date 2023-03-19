package it.revo.revoservice.payload;

import it.revo.revoservice.entity.WeekDays;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {
    private Integer id;

    private String name;

    private Integer courseId;

    private UUID teacherId; //o'qituvchi oka

    private List<UUID> pupilsId; //o'quvchilar tvar

    private String weekDay;

    private String startHours;

    private String endHours;
}
