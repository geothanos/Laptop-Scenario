package gr.knowledge.induction.dto.payload.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    // Role can be empty set and the user will receive the default ROLE_USER
    private Set<String> role;
}
