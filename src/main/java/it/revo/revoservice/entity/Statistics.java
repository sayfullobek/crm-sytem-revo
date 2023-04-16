package it.revo.revoservice.entity;

import it.revo.revoservice.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Statistics extends AbsEntity {
    private Date whenMonth; //hozirgi oyning nomi
    @OneToMany
    private List<Attendance> attendances;

    private double allSum; //bir oylik umumiy soqqa

    private boolean history; //oy tugagandan keyin ushbu statistika arxivega tushadi
}
