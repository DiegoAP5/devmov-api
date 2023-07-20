package casasegura.devmov.services;

import casasegura.devmov.controllers.dtos.responses.AlarmResponse;
import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.responses.OnlyTemperatureResponse;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public BaseResponse getTemperatureByUserId(Long id, LocalDate date) {
        Float[] data = repository.listTemperatureByDayAndUserId(id, date);
        return BaseResponse.builder()
                .data(data)
                .message("Temperature by user")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getTemperatureById() {
        TemperatureResponse response = from(repository.listAllTemperatureByUserId());
        return BaseResponse.builder()
                .data(response)
                .message("Temperature by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getAlarmData() {
        List<AlarmResponse> response = repository.getAlarmData();
        return BaseResponse.builder()
                .data(response)
                .message("Alarm history")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getStatics(Long id, LocalDate date) {
        Float[] data = repository.getTemperatureByDate(id,date);
        //Get mean
        float plus = 0;
        for(int i = 0; i<data.length; i++){
            plus += data[i];
        }
        float mean = plus/data.length;
        //Variance
        float plusSqrt = 0;
        for (float value : data) {
            plusSqrt += Math.pow(value - mean, 2);
        }
        float variance = plusSqrt/data.length;
        //Standard deviation
        float standardDeviation = (float) Math.sqrt(variance);
        //Mean deviation
        float plusDeviation = 0;
        for (float value : data){
            plusDeviation += Math.abs(value - mean);
        }
        float meanDeviation = plusDeviation/data.length;
        //Sample deviation
        float sampleVariance = plusSqrt/(data.length -1);
        float sampleDeviation = (float) Math.sqrt(sampleVariance);
        Map<String, Float> response = new HashMap<>();
        response.put("Mean",mean);
        response.put("Mean deviation",meanDeviation);
        response.put("Standard deviation",standardDeviation);
        response.put("Variance",variance);
        response.put("Sample deviation", sampleDeviation);
        return BaseResponse.builder()
                .data(response)
                .message("Statics data")
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

    private OnlyTemperatureResponse fromT(TemperatureProjection temperature){
        OnlyTemperatureResponse response = new OnlyTemperatureResponse();
        response.setTemperature(temperature.getTemperature());
        response.setTime(temperature.getTime());
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
