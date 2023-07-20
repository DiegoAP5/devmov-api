package casasegura.devmov.services;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.responses.SmokeResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateSmokeRequest;
import casasegura.devmov.entities.Smoke;
import casasegura.devmov.entities.User;
import casasegura.devmov.entities.projections.SmokeProjection;
import casasegura.devmov.repositories.ISmokeRepository;
import casasegura.devmov.services.Interfaces.ISmokeService;
import casasegura.devmov.services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmokeServiceImpl implements ISmokeService {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISmokeRepository repository;

    @Override
    public BaseResponse getSmokeByUserId(Long id, LocalDate date) {
        List<SmokeResponse> response = repository.getSmokeByUserIdAnd(id,date)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Smoke by user")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getSmokeById(Long id) {
        SmokeResponse response = from(repository.getSmokeById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Smoke by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateSmokeRequest request) {
        SmokeResponse response = new SmokeResponse();
        Smoke smoke = new Smoke();
        smoke = create(request, smoke);
        response = from(repository.save(smoke));
        return BaseResponse.builder()
                .data(response)
                .message("Smoke data created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SmokeResponse from(SmokeProjection smoke){
        SmokeResponse response = new SmokeResponse();
        response.setDate(smoke.getDate());
        response.setTime(smoke.getTime());
        response.setSmoke(smoke.getSmoke());
        response.setUserId(smoke.getUserId());
        response.setAlarm(smoke.getAlarm());
        response.setMessage(smoke.getMessage());
        return response;
    }

    private SmokeResponse from(Smoke smoke){
        SmokeResponse response = new SmokeResponse();
        response.setUserId(smoke.getUser().getId());
        response.setDate(smoke.getDate());
        response.setTime(smoke.getTime());
        response.setSmoke(smoke.getSmoke());
        response.setMessage(smoke.getMessage());
        response.setAlarm(smoke.getAlarm());
        return response;
    }

    private Smoke create(CreateSmokeRequest request, Smoke smoke) {
        User user = userService.findUserById(request.getUserId());
        smoke.setUser(user);
        smoke.setSmoke(request.getSmoke());
        smoke.setTime(request.getTime());
        smoke.setDate(request.getDate());
        return smoke;
    }
}
