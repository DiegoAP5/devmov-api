package casasegura.devmov.services;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.responses.MovementResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateMovementRequest;
import casasegura.devmov.entities.Movement;
import casasegura.devmov.entities.User;
import casasegura.devmov.entities.projections.MovementProjection;
import casasegura.devmov.repositories.IMovementRepository;
import casasegura.devmov.services.Interfaces.IMovementService;
import casasegura.devmov.services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements IMovementService {

    @Autowired
    private IMovementRepository repository;

    @Autowired
    private IUserService userService;


    @Override
    public BaseResponse getMovementByUserId(Long id, LocalDate date) {
        List<MovementResponse> response = repository.getMovementByIdAndDate(id,date)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Movement by user")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getMovementById(Long id) {
        MovementResponse response = from(repository.getMovementById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Movement by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateMovementRequest request) {
        MovementResponse response = new MovementResponse();
        Movement movement = new Movement();
        movement = create(request, movement);
        response = from(repository.save(movement));
        return BaseResponse.builder()
                .data(response)
                .message("Movement data created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private MovementResponse from(MovementProjection movement){
        MovementResponse response = new MovementResponse();
        response.setDate(movement.getDate());
        response.setTime(movement.getTime());
        response.setMovement(movement.getMovement());
        response.setUserId(movement.getUserId());
        response.setMessage(movement.getMessage());
        response.setAlarm(movement.getAlarm());
        return response;
    }

    private MovementResponse from(Movement movement){
        MovementResponse response = new MovementResponse();
        response.setUserId(movement.getUser().getId());
        response.setDate(movement.getDate());
        response.setTime(movement.getTime());
        response.setMovement(movement.getMovement());
        response.setMessage(movement.getMessage());
        response.setAlarm(movement.getAlarm());
        return response;
    }

    private Movement create(CreateMovementRequest request, Movement movement) {
        User user = userService.findUserById(request.getUserId());
        movement.setUser(user);
        movement.setMovement(request.getMovement());
        movement.setTime(request.getTime());
        movement.setDate(request.getDate());
        return movement;
    }
}
