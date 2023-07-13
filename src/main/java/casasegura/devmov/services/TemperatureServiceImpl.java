package casasegura.devmov.services;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.responses.TemperatureResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateTemperatureRequest;
import casasegura.devmov.controllers.exception.BaseException;
import casasegura.devmov.entities.Temperature;
import casasegura.devmov.entities.User;
import casasegura.devmov.entities.projections.TemperatureProjection;
import casasegura.devmov.repositories.ITemperatureRepository;
import casasegura.devmov.services.Interfaces.ITemperatureService;
import casasegura.devmov.services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureServiceImpl implements ITemperatureService {

    @Autowired
    private ITemperatureRepository repository;

    @Autowired
    private IUserService userService;
    @Override
    public Temperature findDataByUserId(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseException("Not found"));
    }

    @Override
    public BaseResponse getTemperatureByUserId(Long id, LocalDate date, LocalTime time) {
        List<TemperatureResponse> response = repository.listTemperatureByDayAndUserId(id,date, time)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Temperature by user")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getTemperatureById(Long id) {
        TemperatureResponse response = from(repository.getTemperatureById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Temperature by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getStatics(Long id, LocalDate date) {
        List<TemperatureResponse> data = repository.getTemperatureByDate(id,date)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(data)
                .message("Temperature data")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateTemperatureRequest request) {
        TemperatureResponse response = new TemperatureResponse();
        Temperature temperature = new Temperature();
        temperature = create(request, temperature);
        response = from(repository.save(temperature));
        return BaseResponse.builder()
                .data(response)
                .message("Temperature created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private TemperatureResponse from(TemperatureProjection temperature){
        TemperatureResponse response = new TemperatureResponse();
        response.setTemperature(temperature.getTemperature());
        response.setDate(temperature.getDate());
        response.setTime(temperature.getTime());
        response.setUserId(temperature.getUserId());
        return response;
    }

    private TemperatureResponse from(Temperature temperature){
        TemperatureResponse response = new TemperatureResponse();
        response.setUserId(temperature.getUser().getId());
        response.setDate(temperature.getDate());
        response.setTime(temperature.getTime());
        response.setTemperature(temperature.getTemperature());
        return response;
    }

    private Temperature create(CreateTemperatureRequest request, Temperature temperature) {
        User user = userService.findUserById(request.getUserId());
        temperature.setUser(user);
        temperature.setTemperature(request.getTemperature());
        temperature.setTime(request.getTime());
        temperature.setDate(request.getDate());
        return temperature;
    }
}
