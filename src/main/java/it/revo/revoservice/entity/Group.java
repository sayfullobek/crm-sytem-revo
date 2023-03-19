package it.revo.revoservice.entity;

import it.revo.revoservice.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "groups")
public class Group extends AbsNameEntity {
    @ManyToOne(optional = false)
    private Course course;

    @ManyToOne(optional = false)
    private User teacher; //o'qituvchi oka

    @OneToMany
    private List<User> pupils; //o'quvchilar tvar

    @OneToMany
    private List<WeekDays> weekDays;

    @NotNull
    private String hours;
}
