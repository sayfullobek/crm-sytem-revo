package it.revo.revoservice.entity;

import it.revo.revoservice.entity.template.AbsEntity;
import it.revo.revoservice.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contact extends AbsEntity {
    @Column(nullable = false)
    private String legalName;

    private String address;
}
