package casasegura.devmov.controllers.dtos.responses;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class TemperatureResponse {

    private LocalDate date;

    private LocalTime time;

    private float temperature;

    private Long userId;
}
