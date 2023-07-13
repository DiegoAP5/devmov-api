package casasegura.devmov.services.Interfaces;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateMovementRequest;
import casasegura.devmov.controllers.dtos.resquests.CreateTemperatureRequest;

import java.time.LocalDate;

public interface IMovementService {

    BaseResponse getMovementByUserId(Long id, LocalDate date);

    BaseResponse getMovementById(Long id);


    BaseResponse create(CreateMovementRequest request);

    void delete(Long id);
}
