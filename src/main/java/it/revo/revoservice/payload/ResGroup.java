package it.revo.revoservice.payload;

import it.revo.revoservice.entity.Course;
import it.revo.revoservice.entity.User;
import it.revo.revoservice.entity.WeekDays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResGroup {
    private Integer id;

    private String name;

    private Course course;

    private User teacher; //o'qituvchi oka

    private List<User> pupils; //o'quvchilar tvar

    private List<WeekDays> weekDays;

    private String hours;

    private double profit;

    private Integer pupilSize;

    private Integer profitPupil;

    private double monthProfit;
}
