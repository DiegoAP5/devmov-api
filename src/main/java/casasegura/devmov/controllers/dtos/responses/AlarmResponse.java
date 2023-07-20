package casasegura.devmov.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmResponse {

    private String message;

    private String status;

    private String date;

    private String hour;
}
