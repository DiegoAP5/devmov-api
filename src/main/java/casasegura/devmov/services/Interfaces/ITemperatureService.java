package casasegura.devmov.services.Interfaces;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.responses.TemperatureResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateTemperatureRequest;
import casasegura.devmov.controllers.dtos.resquests.CreateUserRequest;
import casasegura.devmov.entities.Temperature;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ITemperatureService {

    Temperature findDataByUserId(Long id);


    BaseResponse getTemperatureByUserId(Long id, LocalDate date, LocalTime time);

    BaseResponse getTemperatureById(Long id);

    BaseResponse getStatics(Long id, LocalDate date);

    BaseResponse create(CreateTemperatureRequest request);

    void delete(Long id);
}
