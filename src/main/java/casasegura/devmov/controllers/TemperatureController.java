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

    @GetMapping("user/data/{id}/{date}")
    public ResponseEntity<BaseResponse> getTemperatureByIdAndDate(@PathVariable Long id, @PathVariable LocalDate date){
        BaseResponse baseResponse = service.getTemperatureByUserId(id,date);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}/{date}")
    public ResponseEntity<BaseResponse> getStaticsData(@PathVariable Long id, @PathVariable LocalDate date){
        BaseResponse baseResponse = service.getStatics(id,date);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @GetMapping("/last")
    public ResponseEntity<BaseResponse> getTemperatureById(){
        BaseResponse baseResponse = service.getTemperatureById();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("/alarm")
    public ResponseEntity<BaseResponse> getAlarmHistory(){
        BaseResponse baseResponse = service.getAlarmData();
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
