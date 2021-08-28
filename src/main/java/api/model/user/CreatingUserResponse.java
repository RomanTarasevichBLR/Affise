package api.model.user;

import lombok.Data;

@Data
public class CreatingUserResponse {
    private int status;
    private User user;
}
