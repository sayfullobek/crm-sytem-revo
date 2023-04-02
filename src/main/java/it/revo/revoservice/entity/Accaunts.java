package it.revo.revoservice.entity;

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
public class Accaunts extends AbsNameEntity {
    @Column(nullable = false, unique = true)
    private String link;
    
    private String logo;
}
