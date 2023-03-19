package it.revo.revoservice.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private String name;
    private double price;
    private int expireMonth;
}
