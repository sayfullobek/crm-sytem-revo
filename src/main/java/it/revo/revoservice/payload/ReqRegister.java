package it.revo.revoservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqRegister {
    private UUID id;

    private List<UUID> ids;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String parentNumber;

    private Integer courseId;

    private String birthDate;

    private String api;

    private String password;
}
