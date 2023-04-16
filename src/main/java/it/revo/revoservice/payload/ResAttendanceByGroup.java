package it.revo.revoservice.payload;

import it.revo.revoservice.entity.User;
import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResAttendanceByGroup {
    private UUID pupilId;
    private User pupil;
    private boolean active;
}
