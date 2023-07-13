package casasegura.devmov.controllers;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateSmokeRequest;
import casasegura.devmov.services.Interfaces.ISmokeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("smoke")
public class SmokeController {

    @Autowired
    private ISmokeService service;

    @GetMapping("user/{id}/{date}")
    public ResponseEntity<BaseResponse> getTemperatureByIdAndDate(@PathVariable Long id, @PathVariable LocalDate date){
        BaseResponse baseResponse = service.getSmokeByUserId(id,date);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getTemperatureById(@PathVariable Long id){
        BaseResponse baseResponse = service.getSmokeById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateSmokeRequest request){
        BaseResponse baseResponse = service.create(request);
        return  new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
