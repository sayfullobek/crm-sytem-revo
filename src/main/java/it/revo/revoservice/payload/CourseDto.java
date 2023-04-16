package it.revo.revoservice.payload;

import it.revo.revoservice.entity.Attachment;
import lombok.*;
import org.springframework.http.HttpEntity;

import java.util.UUID;

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
    private UUID photoId;
}
