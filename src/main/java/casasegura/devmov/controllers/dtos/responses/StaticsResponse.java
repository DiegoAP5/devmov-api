package casasegura.devmov.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StaticsResponse {

    private float mean;

    private float standardDeviation;

    private float meanDeviation;

    private float variance;
}
