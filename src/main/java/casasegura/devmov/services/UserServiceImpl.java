package casasegura.devmov.services;

import casasegura.devmov.controllers.dtos.responses.BaseResponse;
import casasegura.devmov.controllers.dtos.responses.UserResponse;
import casasegura.devmov.controllers.dtos.resquests.CreateUserRequest;
import casasegura.devmov.controllers.exception.BaseException;
import casasegura.devmov.entities.User;
import casasegura.devmov.repositories.IUserRepository;
import casasegura.devmov.services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BaseException("User not found "));
    }

    @Override
    public User getNoUserByName(String name) {
        return repository.getUserByName(name);
    }

    public User getNoUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    @Override
    public BaseResponse listUsers() {
        List<UserResponse> responses = repository.findAll().stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("User list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getUserById(Long id) {
        UserResponse response = from(findUserById(id));
        return BaseResponse.builder()
                .data(response)
                .message("User by Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getUserByName(String name) {
        UserResponse response = from(getNoUserByName(name));
        return BaseResponse.builder()
                .data(response)
                .message("User by name")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getUserByEmail(String email) {
        UserResponse response = from(getNoUserByEmail(email));
        return BaseResponse.builder()
                .data(response)
                .message("User by email")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = new User();
        user = create(request,user);
        UserResponse response = from(repository.save(user));
        return BaseResponse.builder()
                .data(response)
                .message("user created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        findUserById(id);
        repository.deleteById(id);
    }

    private UserResponse from(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    private User create(CreateUserRequest request, User user) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUsername(request.getPassword());
        return user;
    }
}
