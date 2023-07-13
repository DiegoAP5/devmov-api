package casasegura.devmov.controllers;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateTemperatureRequest;
import casasegura.devmov.services.Interfaces.ITemperatureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("temperature")
public class TemperatureController {

    @Autowired
    private ITemperatureService service;

    @GetMapping("user/{id}/{date}/{time}")
    public ResponseEntity<BaseResponse> getTemperatureByIdAndDate(@PathVariable Long id, @PathVariable LocalDate date, @PathVariable LocalTime time){
        BaseResponse baseResponse = service.getTemperatureByUserId(id,date, time);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}/{date}")
    public ResponseEntity<BaseResponse> getStaticsData(@PathVariable Long id, @PathVariable LocalDate date){
        BaseResponse baseResponse = service.getStatics(id,date);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getTemperatureById(@PathVariable Long id){
        BaseResponse baseResponse = service.getTemperatureById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateTemperatureRequest request){
        BaseResponse baseResponse = service.create(request);
        return  new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
