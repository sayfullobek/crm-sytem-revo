package it.revo.revoservice.payload;

import it.revo.revoservice.entity.User;
import it.revo.revoservice.entity.enums.OperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResHistory {
    private String tableName;
    private UUID objectId;
    private OperationEnum operationEnum;
    private Object object;
    private UUID id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private UUID createdBy;
    private UUID updatedBy;
    private User user;

    public ResHistory(UUID objectId, OperationEnum operationEnum, Object object, UUID id, Timestamp createdAt, Timestamp updatedAt, UUID createdBy, UUID updatedBy, User user) {
        this.objectId = objectId;
        this.operationEnum = operationEnum;
        this.object = object;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.user = user;
    }
}
