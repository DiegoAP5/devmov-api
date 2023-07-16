package casasegura.devmov.controllers.dtos.resquests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
public class AuthenticationRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
