package it.revo.revoservice.entity;

import it.revo.revoservice.entity.template.AbsNameEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class WhereGo extends AbsNameEntity {
    //ushbu class o'quvchilarni qayerda ushbu markazga kelganini statistika uchun bilish
}
