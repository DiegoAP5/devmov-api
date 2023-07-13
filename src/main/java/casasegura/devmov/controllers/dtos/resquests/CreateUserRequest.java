package casasegura.devmov.controllers.dtos.resquests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull @NotBlank
    private String password;

    @NotNull @Email
    private String email;

    private String username;
}
