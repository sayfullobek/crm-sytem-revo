package it.revo.revoservice.payload;

import lombok.*;
import org.springframework.http.HttpEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Integer id;
    private String name;
    private double price;
    private int expireMonth;
    private String description;
    private HttpEntity<?> file;
}
