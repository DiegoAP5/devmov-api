package casasegura.devmov.services.Interfaces;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateSmokeRequest;
import casasegura.devmov.controllers.dtos.resquests.CreateTemperatureRequest;

import java.time.LocalDate;

public interface ISmokeService {

    BaseResponse getSmokeByUserId(Long id, LocalDate date);

    BaseResponse getSmokeById(Long id);


    BaseResponse create(CreateSmokeRequest request);

    void delete(Long id);
}
