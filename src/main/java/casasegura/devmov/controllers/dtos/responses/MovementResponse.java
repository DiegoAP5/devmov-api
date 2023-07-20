package casasegura.devmov.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class MovementResponse {

    private LocalDate date;

    private LocalTime time;

    private float movement;

    private Long userId;

    private String message;

    private String alarm;

}
