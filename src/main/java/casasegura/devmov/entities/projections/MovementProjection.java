package casasegura.devmov.entities.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface MovementProjection {

    float getMovement();

    LocalDate getDate();

    LocalTime getTime();

    Long getUserId();
}
