package casasegura.devmov.controllers;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateMovementRequest;
import casasegura.devmov.services.Interfaces.IMovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("movement")
public class MovementController {
    @Autowired
    private IMovementService service;

    @GetMapping("user/{id}/{date}")
    public ResponseEntity<BaseResponse> getTemperatureByIdAndDate(@PathVariable Long id, @PathVariable LocalDate date){
        BaseResponse baseResponse = service.getMovementByUserId(id,date);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getTemperatureById(@PathVariable Long id){
        BaseResponse baseResponse = service.getMovementById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateMovementRequest request){
        BaseResponse baseResponse = service.create(request);
        return  new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
