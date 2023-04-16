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
public class Contact extends AbsNameEntity {
    @Column(nullable = false, length = 10000)
    private String photoLink;

    private String address;
}
