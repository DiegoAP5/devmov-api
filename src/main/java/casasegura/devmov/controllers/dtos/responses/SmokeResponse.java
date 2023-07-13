package casasegura.devmov.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class SmokeResponse {

    private LocalDate date;

    private LocalTime time;

    private float smoke;

    private Long userId;
}
