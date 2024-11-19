package gr.knowledge.induction.dto.payload.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private List<String> roles;

    // JwtResponseDto Constructor with user principal and the token
    public JwtResponseDto(String accessToken, Long id, String username, String firstname, String lastname, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }
    
}
