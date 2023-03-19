package it.revo.revoservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqRegister {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Integer courseId;

    private String birthDate;

    private String api;
}
