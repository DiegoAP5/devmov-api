package casasegura.devmov.controllers.dtos.resquests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class CreateSmokeRequest {

    @NotNull
    private LocalDate date;

    private LocalTime time;

    @NotNull
    private float smoke;

    private Long userId;
}
