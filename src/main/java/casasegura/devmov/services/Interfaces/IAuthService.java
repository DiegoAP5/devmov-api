package casasegura.devmov.services.Interfaces;


import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.resquests.AuthenticationRequest;

public interface IAuthService {

    BaseResponse authenticate(AuthenticationRequest request);
}
