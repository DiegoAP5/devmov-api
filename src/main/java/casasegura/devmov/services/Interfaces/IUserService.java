package casasegura.devmov.services.Interfaces;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateUserRequest;
import casasegura.devmov.entities.User;

public interface IUserService {

    User findUserById(Long id);

    User getNoUserByName(String name);

    BaseResponse listUsers();

    BaseResponse getUserById(Long id);

    BaseResponse getUserByName(String name);

    BaseResponse getUserByEmail(String email);

    BaseResponse create(CreateUserRequest request);



    void delete(Long id);
}
