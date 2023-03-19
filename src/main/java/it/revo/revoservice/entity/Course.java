package it.revo.revoservice.entity;

import it.revo.revoservice.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "courses")
public class Course extends AbsNameEntity {
    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int expireMonth; //ushbu kurs necha oy davom etadi
}
