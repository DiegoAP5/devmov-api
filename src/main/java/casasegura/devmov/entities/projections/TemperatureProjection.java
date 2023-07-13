package casasegura.devmov.entities.projections;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public interface TemperatureProjection {

    float getTemperature();

    LocalDate getDate();

    LocalTime getTime();

    Long getUserId();
}
