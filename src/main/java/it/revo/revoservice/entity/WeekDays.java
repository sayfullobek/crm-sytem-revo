package it.revo.revoservice.entity;

import it.revo.revoservice.entity.enums.WeekDayName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "week_days")
public class WeekDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private WeekDayName weekDayName;

    public WeekDays(WeekDayName weekDayName) {
        this.weekDayName = weekDayName;
    }
}
