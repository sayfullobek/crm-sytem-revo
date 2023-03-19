package it.revo.revoservice.entity;

import it.revo.revoservice.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Attendance extends AbsEntity {
    @ManyToOne(optional = false)
    private WeekDays weekDays; //qaysi kuni dars boladi

    private double sum; //har darsda oquvchi qancha pulini yoqotadi

    private String date; //sana

    private boolean active; //darsga keldi ketti
}
