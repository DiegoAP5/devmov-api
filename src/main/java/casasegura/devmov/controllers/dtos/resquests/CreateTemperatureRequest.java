package casasegura.devmov.controllers.dtos.resquests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter @Setter
public class CreateTemperatureRequest {
    @NotNull
    private LocalDate date;

    private LocalTime time;

    @NotNull
    private float temperature;

    private Long userId;
}
