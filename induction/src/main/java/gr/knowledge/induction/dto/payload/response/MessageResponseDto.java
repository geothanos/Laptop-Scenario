package gr.knowledge.induction.dto.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDto {

    private String message;

    public MessageResponseDto(String message) {
        this.message = message;
    }
    
}
