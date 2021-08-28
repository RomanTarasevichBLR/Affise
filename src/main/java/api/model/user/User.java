package api.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class User {
    private String id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String username;
    private List<String> roles;
    private String skype;
    private String work_hours;
    private String api_key;
    private String type;
    private String avatar;
    private String updated_at;
    private String created_at;
    private String last_login_at;
}
