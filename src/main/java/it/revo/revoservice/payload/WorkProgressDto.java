package it.revo.revoservice.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkProgressDto {
    private String name;
    private Integer progress;
}
