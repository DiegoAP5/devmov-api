package casasegura.devmov.entities.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SmokeProjection {

    float getSmoke();

    LocalDate getDate();

    LocalTime getTime();

    Long getUserId();

    String getMessage();

    String getAlarm();
}
