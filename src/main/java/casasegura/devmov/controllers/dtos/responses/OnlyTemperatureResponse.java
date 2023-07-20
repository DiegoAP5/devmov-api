package casasegura.devmov.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class OnlyTemperatureResponse {

    private float temperature;

    private LocalTime time;
}
