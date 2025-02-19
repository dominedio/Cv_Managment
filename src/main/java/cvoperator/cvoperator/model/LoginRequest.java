package cvoperator.cvoperator.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull (message = "User not found")
    @Email (message = "email not valid")
    private String email;
    @NotNull (message = "wrong password")
    private String password;
}