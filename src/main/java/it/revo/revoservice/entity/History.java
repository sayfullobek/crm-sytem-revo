package it.revo.revoservice.entity;

import it.revo.revoservice.config.JpaConverterJson;
import it.revo.revoservice.entity.enums.OperationEnum;
import it.revo.revoservice.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History extends AbsEntity {

    private String tableName;//O'ZGARISH BULGAN TABLE NOMINI QAYD QILIB BORADI!!!

    private UUID objectId;//O'ZGARISH  BULAYOTGAN OBJECTNI ID SI!!

    @Enumerated(EnumType.STRING)
    private OperationEnum operationEnum;// QANAQA O'ZGARISH BULAYOTGANINI QAYD ETADIGAN ENUM!!!

    @Convert(converter = JpaConverterJson.class)
    @Column(columnDefinition = "text")
    private Object object; //O'ZGARISH BULAYOTGAN OBJECTNI O'ZI


}
